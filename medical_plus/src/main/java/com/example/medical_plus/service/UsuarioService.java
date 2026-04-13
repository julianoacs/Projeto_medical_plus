package com.example.medical_plus.service;

import com.example.medical_plus.dto.ExameDTO;
import com.example.medical_plus.model.Usuario;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import com.example.medical_plus.service.UsuarioService;

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

    // 🔥 LISTAR TODOS EXAMES (ANTIGO - MANTIDO)
    public List<String> listarTodosExames() {

        List<String> exames = new ArrayList<>();

        for (Usuario u : usuarios) {
            if ("MEDICO".equalsIgnoreCase(u.getTipo())) {
                exames.addAll(u.getExames());
            }
        }

        return exames.stream()
                .distinct()
                .toList();
    }

    // 🔥 NOVO — LISTAR EXAMES COM MÉDICO (DTO)
    public List<ExameDTO> listarTodosExamesComMedico() {

        List<ExameDTO> lista = new ArrayList<>();

        for (Usuario u : usuarios) {

            if ("MEDICO".equalsIgnoreCase(u.getTipo())) {

                for (String exame : u.getExames()) {

                    lista.add(new ExameDTO(
                            exame,
                            u.getNome()
                    ));
                }
            }
        }

        return lista;
    }

    // 🔥 REMOVER EXAME GLOBAL (ADMIN)
    public void removerExameDoMedico(String exame, String nomeMedico) {

        for (Usuario u : usuarios) {

            if ("MEDICO".equalsIgnoreCase(u.getTipo())
                    && u.getNome().equalsIgnoreCase(nomeMedico)) {

                u.getExames().removeIf(e ->
                        e.equalsIgnoreCase(exame)
                );

                break; // 🔥 para evitar remover de outros médicos
            }
        }
    }

    // 🔥 ADICIONAR EXAME AO MÉDICO (COM VALIDAÇÃO)
    public void adicionarExameAoMedico(Usuario usuario, String exame) {

        if (usuario == null || !"MEDICO".equalsIgnoreCase(usuario.getTipo())) {
            return;
        }

        boolean jaExiste = usuario.getExames().stream()
                .anyMatch(e -> e.equalsIgnoreCase(exame));

        if (!jaExiste) {
            usuario.getExames().add(exame);
        }
    }

    // 🔥 BUSCAR USUÁRIO POR EMAIL
    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarMedicosPorExame(String exame) {
        return listarMedicos().stream()
                .filter(m -> m.getExames().contains(exame))
                .collect(Collectors.toList());
    }
}