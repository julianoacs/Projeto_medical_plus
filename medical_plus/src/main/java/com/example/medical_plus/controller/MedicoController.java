package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import com.example.medical_plus.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MedicoController {

    @Autowired
    private UsuarioService usuarioService;

    // ✅ ADICIONAR EXAME
    @PostMapping("/medico/adicionar-exame")
    public String adicionarExame(String exame, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // 🔒 VALIDAÇÃO
        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuarioService.adicionarExameAoMedico(usuario, exame);

        return "redirect:/profile?aba=exames";
    }

    // ❌ REMOVER EXAME DO MÉDICO
    @GetMapping("/medico/remover-exame")
    public String removerExame(String exame, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // 🔒 VALIDAÇÃO
        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuario.getExames().removeIf(e ->
                e.equalsIgnoreCase(exame)
        );

        return "redirect:/profile?aba=exames";
    }
}