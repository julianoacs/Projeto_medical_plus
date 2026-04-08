package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import com.example.medical_plus.service.AgendamentoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        // 🔥 apenas agendamentos do usuário logado
        model.addAttribute(
                "agendamentos",
                agendamentoService.listarPorUsuario(usuario.getEmail())
        );

        return "profile";
    }
}