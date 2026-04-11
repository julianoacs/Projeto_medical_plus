package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MedicoController {

    // ✅ ADICIONAR EXAME
    @PostMapping("/medico/adicionar-exame")
    public String adicionarExame(String exame, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuario.adicionarExame(exame);

        return "redirect:/profile";
    }

    // REMOVER EXAME
    @GetMapping("/medico/remover-exame")
    public String removerExame(String exame, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuario.removerExame(exame);

        return "redirect:/profile";
    }
}