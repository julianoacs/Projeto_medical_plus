package com.example.medical_plus.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String tipo;

    // 🔥 NOVO: exames do médico
    private List<String> exames = new ArrayList<>();

    public Usuario(String nome, String email, String senha, String tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    // GETTERS

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

    // 🔥 MÉTODO PARA ADICIONAR EXAME (USADO PELO MÉDICO)

    public void adicionarExame(String exame) {
        this.exames.add(exame);
    }
}