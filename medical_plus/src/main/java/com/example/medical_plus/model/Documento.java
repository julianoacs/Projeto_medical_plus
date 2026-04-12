package com.example.medical_plus.model;

public class Documento {

    private String nomeArquivo;
    private String status; // PENDENTE / APROVADO / REJEITADO

    public Documento(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.status = "PENDENTE";
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}