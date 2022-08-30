package com.example.futebolescqrs.commands;

import com.example.futebolescqrs.commands.eventos.EventoTempoPartida;
import com.example.futebolescqrs.commands.eventos.EventoLancePartida;
import com.example.futebolescqrs.commands.eventos.EventoNovaPartida;
import com.example.futebolescqrs.entidadesWrite.EventStorePartida;
import com.example.futebolescqrs.projectors.PartidaEventStoreProjector;
import com.example.futebolescqrs.repositoriosWrite.EventStorePartidaRepository;
import com.example.futebolescqrs.service.PartidaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas/write")
public class PartidaCommand {

    @Autowired
    private EventStorePartidaRepository repository;

    @Autowired
    private PartidaService service;

    @Autowired
    private PartidaEventStoreProjector projector;

    @PostMapping("/nova-partida")
    @ResponseStatus(HttpStatus.CREATED)
    public long adicionaNovaPartida(@RequestBody EventoNovaPartida evento) throws JsonProcessingException {

        long id = service.adicionaEventoNovaPartida(evento);

        return id;
    }

    @PostMapping("/evento-partida")
    public ResponseEntity<String> adicionaEventoPartida(@RequestBody EventoLancePartida evento) throws JsonProcessingException {

        List<EventStorePartida> partida = repository.findByIdEntidade(evento.getIdEntidade());
        if (!partida.isEmpty()) {

            service.adicionaLancePartida(evento);

            return ResponseEntity.created(null).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/desfaz-evento-partida")
    public ResponseEntity<String> desfazEventoPartida(@RequestBody EventoLancePartida evento) throws JsonProcessingException {

        List<EventStorePartida> partida = repository.findByIdEntidade(evento.getIdEntidade());
        if (!partida.isEmpty()) {

            service.adicionaLancePartida(evento);

            return ResponseEntity.created(null).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/tempo-partida")
    public ResponseEntity<String> marcaTempoPartida(@RequestBody EventoTempoPartida evento) throws JsonProcessingException {

        List<EventStorePartida> partida = repository.findByIdEntidade(evento.getIdEntidade());
        if (!partida.isEmpty()) {

            service.adicionaFimPartida(evento);
            if(evento.getTipoEvento().equals("FIM_JOGO")) {
                projector.project(evento.getIdEntidade());
            }

            return ResponseEntity.created(null).build();
        }
        return ResponseEntity.notFound().build();
    }
}
