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

    // ✅ APROVAR
    @GetMapping("/admin/aprovar-documento")
    public String aprovar(String email, String arquivo) {

        Usuario u = usuarioService.buscarPorEmail(email);

        if (u != null) {
            // Aprovar documento específico
            u.getDocumentos().stream()
                    .filter(d -> d.getNomeArquivo().equals(arquivo))
                    .findFirst()
                    .ifPresent(d -> d.setStatus("APROVADO"));

            // 🔥 Verificar se todos os documentos foram aprovados
            boolean todosAprovados = u.getDocumentos().stream()
                    .allMatch(d -> "APROVADO".equals(d.getStatus()));
            if (todosAprovados) {
                u.setStatus("APROVADO");
            }
        }

        return "redirect:/profile?aba=medicos";
    }

    // ❌ REJEITAR
    @GetMapping("/admin/rejeitar-documento")
    public String rejeitar(String email, String arquivo) {

        Usuario u = usuarioService.buscarPorEmail(email);

        if (u != null) {
            // Rejeitar documento específico
            u.getDocumentos().stream()
                    .filter(d -> d.getNomeArquivo().equals(arquivo))
                    .findFirst()
                    .ifPresent(d -> d.setStatus("REJEITADO"));

            // 🔥 Se houver algum rejeitado, status do médico volta para pendente
            boolean algumRejeitado = u.getDocumentos().stream()
                    .anyMatch(d -> "REJEITADO".equals(d.getStatus()));
            if (algumRejeitado) {
                u.setStatus("PENDENTE");
            }
        }

        return "redirect:/profile?aba=medicos";
    }
}