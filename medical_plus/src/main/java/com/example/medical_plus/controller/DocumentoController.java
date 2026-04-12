package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentoController {

    // 🔥 UPLOAD DE DOCUMENTO (AGORA MULTI)
    @PostMapping("/medico/upload-documento")
    public String uploadDocumento(@RequestParam("arquivo") MultipartFile arquivo,
                                  HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // 🔒 PROTEÇÃO
        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        if (!arquivo.isEmpty()) {

            // 🔥 agora adiciona na LISTA
            String nomeArquivo = arquivo.getOriginalFilename();

            usuario.adicionarDocumento(nomeArquivo);
        }

        return "redirect:/profile?aba=documentos";
    }

    // 🔥 REMOVER DOCUMENTO (MÉDICO)
    @GetMapping("/medico/remover-documento")
    public String removerDocumento(@RequestParam String arquivo,
                                   HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuario.removerDocumento(arquivo);

        return "redirect:/profile?aba=documentos";
    }
}