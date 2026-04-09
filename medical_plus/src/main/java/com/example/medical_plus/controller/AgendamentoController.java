package com.example.medical_plus.controller;

import com.example.medical_plus.model.Agendamento;
import com.example.medical_plus.model.Usuario;
import com.example.medical_plus.service.AgendamentoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // ABRIR TELA (PROTEGIDA)
    @GetMapping("/agendamentos")
    public String agendamentos(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        List<String> exames = List.of(
                "Hemograma Completo",
                "Raio-X",
                "Ressonância Magnética",
                "Tomografia Computadorizada",
                "Ultrassonografia"
        );

        List<String> medicos = List.of(
                "Dr. João Silva",
                "Dra. Maria Oliveira",
                "Dr. Carlos Souza",
                "Dra. Ana Costa"
        );

        model.addAttribute("usuario", usuario);
        model.addAttribute("exames", exames);
        model.addAttribute("medicos", medicos);

        return "agendamentos";
    }

    // SALVAR AGENDAMENTO
    @PostMapping("/agendar")
    public String agendar(String exame,
                          String medico,
                          String data,
                          String hora,
                          HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        Agendamento ag = new Agendamento(
                exame,
                medico,
                data,
                hora,
                usuario.getEmail() // vínculo com usuário
        );

        agendamentoService.salvar(ag);

        return "redirect:/profile";
    }

    // CANCELAR AGENDAMENTO
    @GetMapping("/cancelar")
    public String cancelar(int index, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        // SE FOR ADMIN
        if ("ADMIN".equalsIgnoreCase(usuario.getTipo())) {
            agendamentoService.removerAdmin(index);
        } else {
            agendamentoService.remover(index, usuario.getEmail());
        }

        return "redirect:/profile?aba=historico";
    }
}