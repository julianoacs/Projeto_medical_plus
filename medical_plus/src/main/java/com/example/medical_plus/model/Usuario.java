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

    // 🔥 NOVO — LISTA DE DOCUMENTOS (MULTI)
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

    public List<String> getExames() {
        return exames;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    // =====================
    // MÉTODOS DE NEGÓCIO
    // =====================

    // 🔥 ADICIONAR EXAME (COM VALIDAÇÃO)
    public void adicionarExame(String exame) {

        if (exame == null || exame.trim().isEmpty()) {
            return;
        }

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

    // =====================
    // 🔥 DOCUMENTOS
    // =====================

    // ADICIONAR DOCUMENTO
    public void adicionarDocumento(String nomeArquivo) {

        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            return;
        }

        documentos.add(new Documento(nomeArquivo));
    }

    // REMOVER DOCUMENTO
    public void removerDocumento(String nomeArquivo) {
        documentos.removeIf(doc ->
                doc.getNomeArquivo().equalsIgnoreCase(nomeArquivo)
        );
    }

    // ATUALIZAR STATUS DO DOCUMENTO
    public void atualizarStatusDocumento(String nomeArquivo, String status) {

        documentos.stream()
                .filter(doc -> doc.getNomeArquivo().equalsIgnoreCase(nomeArquivo))
                .findFirst()
                .ifPresent(doc -> doc.setStatus(status));
    }
}