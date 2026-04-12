package com.example.medical_plus.controller;

import com.example.medical_plus.model.Usuario;
import com.example.medical_plus.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

@Controller
public class MedicoController {

    @Autowired
    private UsuarioService usuarioService;

    // ✅ ADICIONAR EXAME
    @PostMapping("/medico/adicionar-exame")
    public String adicionarExame(String exame, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // 🔒 VALIDAÇÃO
        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuarioService.adicionarExameAoMedico(usuario, exame);

        return "redirect:/profile?aba=exames";
    }

    // ❌ REMOVER EXAME DO MÉDICO
    @GetMapping("/medico/remover-exame")
    public String removerExame(String exame, HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // 🔒 VALIDAÇÃO
        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return "redirect:/login";
        }

        usuario.getExames().removeIf(e ->
                e.equalsIgnoreCase(exame)
        );

        return "redirect:/profile?aba=exames";
    }

    @PostMapping("/medico/upload-documento")
    public String uploadDocumentos(@RequestParam("arquivos") MultipartFile[] arquivos,
                                   HttpSession session) throws IOException {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login";
        }

        for (MultipartFile arquivo : arquivos) {
            if (!arquivo.isEmpty()) {
                // Salva o arquivo no servidor
                Path path = Paths.get("uploads/" + arquivo.getOriginalFilename());
                Files.write(path, arquivo.getBytes());

                // Adiciona à lista de documentos do usuário
                usuario.adicionarDocumento(arquivo.getOriginalFilename());
            }
        }

        return "redirect:/profile?aba=documentos";
    }

    @GetMapping("/medico/remover-documento")
    public String removerDocumento(String nome, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            usuario.removerDocumento(nome);
            // opcional: deletar o arquivo do servidor
            try {
                Files.deleteIfExists(Paths.get("uploads/" + nome));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/profile?aba=documentos";
    }
}