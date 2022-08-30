package com.example.futebolescqrs.commands;

import com.example.futebolescqrs.commands.dtos.JogadorDto;
import com.example.futebolescqrs.entidadesWrite.Jogador;
import com.example.futebolescqrs.projectors.JogadorProjector;
import com.example.futebolescqrs.repositoriosWrite.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogadores/write")
public class JogadorCommand {

    @Autowired
    private JogadorRepository repository;

    @Autowired
    private JogadorProjector projector;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionaJogador(@RequestBody JogadorDto jogadorDto) {

        Jogador jogador = jogadorDto.toModel();

        repository.save(jogador);
        projector.project(jogador);

    }
}
