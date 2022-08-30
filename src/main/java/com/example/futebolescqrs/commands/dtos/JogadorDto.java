package com.example.futebolescqrs.commands.dtos;


import com.example.futebolescqrs.entidadesWrite.Jogador;

public class JogadorDto {

    private String nome;

    public String getNome() {
        return nome;
    }

    public Jogador toModel() {
        return new Jogador(nome);
    }
}
