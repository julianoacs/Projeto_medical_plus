package com.example.medical_plus.service;

import com.example.medical_plus.model.Agendamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final List<Agendamento> agendamentos = new ArrayList<>();

    // SALVAR AGENDAMENTO
    public void salvar(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    // LISTAR APENAS DO USUÁRIO
    public List<Agendamento> listarPorUsuario(String email) {
        return agendamentos.stream()
                .filter(a -> a.getEmailUsuario().equals(email))
                .collect(Collectors.toList());
    }

    // LISTAR TODOS (ADMIN)
    public List<Agendamento> listarTodos() {
        return agendamentos;
    }

    // REMOVER AGENDAMENTO (USUÁRIO)
    public void remover(int index, String email) {

        List<Agendamento> listaUsuario = listarPorUsuario(email);

        if (index >= 0 && index < listaUsuario.size()) {
            Agendamento ag = listaUsuario.get(index);
            agendamentos.remove(ag);
        }
    }

    // REMOVER QUALQUER AGENDAMENTO (ADMIN FUTURO)
    public void removerAdmin(int index) {

        if (index >= 0 && index < agendamentos.size()) {
            agendamentos.remove(index);
        }
    }
}