package com.example.medical_plus.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MedicosController {

    @GetMapping("/medicos")
    public String listarMedicos(Model model, HttpSession session) {

        // Lista de médicos temporaria
        List<String[]> medicos = List.of(
                new String[]{"Dr. João Silva", "Cardiologista"},
                new String[]{"Dra. Maria Oliveira", "Dermatologista"},
                new String[]{"Dr. Carlos Souza", "Ortopedista"},
                new String[]{"Dra. Ana Costa", "Pediatra"},
                new String[]{"Dr. Paulo Mendes", "Neurologista"}
        );

        // envia médicos para o HTML
        model.addAttribute("medicos", medicos);

        // envia usuário logado (IMPORTANTE)
        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "medicos";
    }
}