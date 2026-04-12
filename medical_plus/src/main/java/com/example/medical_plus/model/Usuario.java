package com.example.medical_plus.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String tipo;

    // 🔥 EXAMES DO MÉDICO
    private List<String> exames = new ArrayList<>();

    // 🔥 NOVO — DOCUMENTO DO MÉDICO
    private String documento;

    // 🔥 NOVO — STATUS DO MÉDICO (FUTURO ADMIN)
    private String status = "PENDENTE"; // PENDENTE / APROVADO / REJEITADO

    public Usuario(String nome, String email, String senha, String tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    // =====================
    // GETTERS
    // =====================

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public List<String> getExames() {
        return exames;
    }

    public String getDocumento() {
        return documento;
    }

    public String getStatus() {
        return status;
    }

    // =====================
    // SETTERS
    // =====================

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // =====================
    // MÉTODOS DE NEGÓCIO
    // =====================

    // 🔥 ADICIONAR EXAME (COM VALIDAÇÃO)
    public void adicionarExame(String exame) {

        if (exame == null || exame.trim().isEmpty()) {
            return;
        }

        // evita duplicados (case insensitive)
        boolean jaExiste = exames.stream()
                .anyMatch(e -> e.equalsIgnoreCase(exame));

        if (!jaExiste) {
            exames.add(exame);
        }
    }

    // 🔥 REMOVER EXAME
    public void removerExame(String exame) {
        exames.removeIf(e -> e.equalsIgnoreCase(exame));
    }
}