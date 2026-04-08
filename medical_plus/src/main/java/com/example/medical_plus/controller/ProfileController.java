package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import com.example.medical_plus.service.AgendamentoService;
import com.example.medical_plus.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        // 🔥 DIFERENCIAÇÃO ADMIN x USUÁRIO
        if ("ADMIN".equalsIgnoreCase(usuario.getTipo())) {

            // ADMIN vê tudo
            model.addAttribute("agendamentos", agendamentoService.listarTodos());

            model.addAttribute("pacientes", usuarioService.listarPacientes());
            model.addAttribute("medicos", usuarioService.listarMedicos());

        } else {

            // usuário comum vê só os dele
            model.addAttribute(
                    "agendamentos",
                    agendamentoService.listarPorUsuario(usuario.getEmail())
            );
        }

        return "profile";
    }
}