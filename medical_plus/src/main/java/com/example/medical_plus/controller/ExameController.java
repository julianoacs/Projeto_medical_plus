package com.example.medical_plus.controller;

import com.example.medical_plus.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExameController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/exames")
    public String listarExames(Model model, HttpSession session) {

        // 🔥 AGORA OS EXAMES VÊM DOS MÉDICOS
        model.addAttribute("exames", usuarioService.listarTodosExames());

        // mantém usuário logado no header
        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "exames";
    }
}