package com.example.medical_plus.model;

public class Agendamento {

    private String exame;
    private String medico;
    private String data;
    private String hora;
    private String emailUsuario; // 🔥 vincula ao usuário

    public Agendamento(String exame, String medico, String data, String hora, String emailUsuario) {
        this.exame = exame;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.emailUsuario = emailUsuario;
    }

    public String getExame() {
        return exame;
    }

    public String getMedico() {
        return medico;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }
}