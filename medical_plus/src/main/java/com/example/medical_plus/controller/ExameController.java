package com.example.medical_plus.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExameController {

    @GetMapping("/exames")
    public String listarExames(Model model, HttpSession session) {

        // Lista de exames temporaria
        List<String> exames = List.of(
                "Hemograma Completo",
                "Raio-X",
                "Ressonância Magnética",
                "Tomografia Computadorizada",
                "Ultrassonografia",
                "Eletrocardiograma"
        );

        // envia exames para o HTML
        model.addAttribute("exames", exames);

        // envia usuário logado (IMPORTANTE)
        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "exames";
    }
}