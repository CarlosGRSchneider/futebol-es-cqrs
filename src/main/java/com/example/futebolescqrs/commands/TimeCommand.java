package com.example.futebolescqrs.commands;

import com.example.futebolescqrs.commands.dtos.TimeDto;
import com.example.futebolescqrs.entidadesWrite.Time;
import com.example.futebolescqrs.projectors.TimeProjector;
import com.example.futebolescqrs.repositoriosWrite.JogadorRepository;
import com.example.futebolescqrs.repositoriosWrite.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/times/write")
public class TimeCommand {

    @Autowired
    private TimeRepository repository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeProjector projector;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastraTime(@RequestBody TimeDto timeDto) {

        Time time = timeDto.toModel(jogadorRepository);

        repository.save(time);
        projector.project(time);

    }
}
