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

    // =============================
    // 🔥 REMOVER EXAME GLOBAL
    // =============================
    @GetMapping("/admin/remover-exame")
    public String removerExame(String exame, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"ADMIN".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuarioService.removerExameGlobal(exame);

        return "redirect:/profile?aba=examesAdmin";
    }

    // =============================
    // ✅ APROVAR DOCUMENTO
    // =============================
    @GetMapping("/admin/aprovar-documento")
    public String aprovarDocumento(String email, String arquivo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"ADMIN".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        Usuario medico = usuarioService.buscarPorEmail(email);

        if (medico != null) {
            medico.atualizarStatusDocumento(arquivo, "APROVADO");
        }

        return "redirect:/profile?aba=medicos";
    }

    // =============================
    // ❌ REJEITAR DOCUMENTO
    // =============================
    @GetMapping("/admin/rejeitar-documento")
    public String rejeitarDocumento(String email, String arquivo, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"ADMIN".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        Usuario medico = usuarioService.buscarPorEmail(email);

        if (medico != null) {
            medico.atualizarStatusDocumento(arquivo, "REJEITADO");
        }

        return "redirect:/profile?aba=medicos";
    }
}