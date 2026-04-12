package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentoController {

    @PostMapping("/medico/upload-documento")
    public String uploadDocumento(@RequestParam("arquivo") MultipartFile arquivo,
                                  HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        if (!arquivo.isEmpty()) {
            // 🔥 aqui estamos salvando só o nome (simples)
            usuario.setDocumento(arquivo.getOriginalFilename());
        }

        return "redirect:/profile?aba=documentos";
    }
}