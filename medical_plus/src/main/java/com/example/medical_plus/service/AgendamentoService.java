package com.example.medical_plus.service;

import com.example.medical_plus.model.Agendamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final List<Agendamento> agendamentos = new ArrayList<>();

    public void salvar(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    // 🔥 LISTAR APENAS DO USUÁRIO LOGADO
    public List<Agendamento> listarPorUsuario(String email) {
        return agendamentos.stream()
                .filter(a -> a.getEmailUsuario().equals(email))
                .collect(Collectors.toList());
    }

    // 🔥 REMOVER APENAS DO USUÁRIO
    public void remover(int index, String email) {

        List<Agendamento> listaUsuario = listarPorUsuario(email);

        if (index >= 0 && index < listaUsuario.size()) {
            Agendamento ag = listaUsuario.get(index);
            agendamentos.remove(ag);
        }
    }
}