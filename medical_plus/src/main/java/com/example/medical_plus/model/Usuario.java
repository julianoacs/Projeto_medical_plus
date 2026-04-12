package com.example.medical_plus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import com.example.medical_plus.model.Documento;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String tipo;

    // 🔥 EXAMES DO MÉDICO
    private List<String> exames = new ArrayList<>();

    // 🔥 NOVO — LISTA DE DOCUMENTOS
    private List<Documento> documentos = new ArrayList<>();

    // 🔥 NOVO — STATUS DO MÉDICO (FUTURO ADMIN)
    private String status = "PENDENTE"; // PENDENTE / APROVADO / REJEITADO

    // 🔥 NOVO — ESPECIALIDADE DO MÉDICO
    private String especialidade;

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

    public List<Documento> getDocumentos() { return documentos; }

    public String getStatus() {
        return status;
    }

    public String getEspecialidade() { return especialidade; }

    // =====================
    // SETTERS
    // =====================

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

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

    // 🔥 ADICIONAR DOCUMENTO
    public void adicionarDocumento(String nomeArquivo) {
        documentos.add(new Documento(nomeArquivo));
    }

    // 🔥 REMOVER DOCUMENTO
    public void removerDocumento(String nomeArquivo) {
        documentos.removeIf(d -> d.getNomeArquivo().equals(nomeArquivo));
    }

    // 🔥 REMOVER EXAME
    public void removerExame(String exame) {
        exames.removeIf(e -> e.equalsIgnoreCase(exame));
    }

    public void definirEspecialidade(String especialidade) {
        if (especialidade != null && !especialidade.trim().isEmpty()) {
            this.especialidade = especialidade;
        }
    }
}