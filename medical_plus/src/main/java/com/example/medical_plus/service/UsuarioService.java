package com.example.medical_plus.service;

import com.example.medical_plus.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        // 🔥 ADMIN PADRÃO
        usuarios.add(new Usuario(
                "Administrador",
                "admin@medical.com",
                "123",
                "ADMIN"
        ));
    }

    // ✅ SALVAR USUÁRIO
    public void salvar(Usuario usuario) {
        usuarios.add(usuario);
    }

    // ✅ LOGIN
    public Usuario autenticar(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    // ✅ LISTAR PACIENTES
    public List<Usuario> listarPacientes() {
        return usuarios.stream()
                .filter(u -> u.getTipo().equalsIgnoreCase("PACIENTE"))
                .toList();
    }

    // ✅ LISTAR MÉDICOS
    public List<Usuario> listarMedicos() {
        return usuarios.stream()
                .filter(u -> u.getTipo().equalsIgnoreCase("MEDICO"))
                .toList();
    }

    // 🔥 NOVO — LISTAR TODOS OS EXAMES DOS MÉDICOS
    public List<String> listarTodosExames() {

        List<String> exames = new ArrayList<>();

        for (Usuario u : usuarios) {

            if ("MEDICO".equalsIgnoreCase(u.getTipo())) {
                exames.addAll(u.getExames());
            }
        }

        // 🔥 REMOVER DUPLICADOS
        return exames.stream()
                .distinct()
                .toList();
    }
}