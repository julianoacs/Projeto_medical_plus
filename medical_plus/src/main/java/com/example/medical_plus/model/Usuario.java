package com.example.medical_plus.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String tipo;

    // 🔥 NOVO — ESPECIALIDADE DO MÉDICO
    private String especialidade;

    // 🔥 EXAMES DO MÉDICO
    private List<String> exames = new ArrayList<>();

    // 🔥 DOCUMENTOS
    private List<Documento> documentos = new ArrayList<>();

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

    public String getEspecialidade() {
        return especialidade;
    }

    public List<String> getExames() {
        return exames;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    // =====================
    // SETTERS
    // =====================

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    // =====================
    // EXAMES
    // =====================

    public void adicionarExame(String exame) {

        if (exame == null || exame.trim().isEmpty()) return;

        boolean existe = exames.stream()
                .anyMatch(e -> e.equalsIgnoreCase(exame));

        if (!existe) {
            exames.add(exame);
        }
    }

    public void removerExame(String exame) {
        exames.removeIf(e -> e.equalsIgnoreCase(exame));
    }

    // =====================
    // DOCUMENTOS
    // =====================

    public void adicionarDocumento(String nomeArquivo) {

        if (nomeArquivo == null || nomeArquivo.isEmpty()) return;

        documentos.add(new Documento(nomeArquivo));
    }

    public void removerDocumento(String nomeArquivo) {
        documentos.removeIf(doc ->
                doc.getNomeArquivo().equalsIgnoreCase(nomeArquivo)
        );
    }

    public void atualizarStatusDocumento(String nomeArquivo, String status) {

        documentos.stream()
                .filter(doc -> doc.getNomeArquivo().equalsIgnoreCase(nomeArquivo))
                .findFirst()
                .ifPresent(doc -> doc.setStatus(status));
    }
}