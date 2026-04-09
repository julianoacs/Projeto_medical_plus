package com.example.medical_plus.service;

import com.example.medical_plus.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        // ADMIN PADRÃO
        usuarios.add(new Usuario(
                "Administrador",
                "admin@medical.com",
                "123",
                "ADMIN"
        ));
    }

    public void salvar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarPacientes() {
        return usuarios.stream()
                .filter(u -> u.getTipo().equalsIgnoreCase("paciente"))
                .toList();
    }

    public List<Usuario> listarMedicos() {
        return usuarios.stream()
                .filter(u -> u.getTipo().equalsIgnoreCase("medico"))
                .toList();
    }
}