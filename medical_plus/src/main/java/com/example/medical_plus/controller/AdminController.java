package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import com.example.medical_plus.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    // 🔥 REMOVER EXAME GLOBAL (ADMIN)
    @GetMapping("/admin/remover-exame")
    public String removerExame(String exame, String medico, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"ADMIN".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuarioService.removerExameDoMedico(exame, medico);

        return "redirect:/profile?aba=examesAdmin";
    }
}