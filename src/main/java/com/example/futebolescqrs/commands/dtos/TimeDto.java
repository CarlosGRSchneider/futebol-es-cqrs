package com.example.futebolescqrs.commands.dtos;

import com.example.futebolescqrs.entidadesWrite.Jogador;
import com.example.futebolescqrs.entidadesWrite.Time;
import com.example.futebolescqrs.repositoriosWrite.JogadorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeDto {

    private String nome;
    private List<Long> idJogadores = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public List<Long> getIdJogadores() {
        return idJogadores;
    }

    public Time toModel(JogadorRepository repository) {

        List<Jogador> jogadores = idJogadores.stream().map(jogador -> repository.findById(jogador).orElseThrow()).collect(Collectors.toList());

        return new Time(nome, jogadores);
    }
}
