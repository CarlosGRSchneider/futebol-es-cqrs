package com.example.futebolescqrs.service;

import com.example.futebolescqrs.commands.eventos.EventoTempoPartida;
import com.example.futebolescqrs.commands.eventos.EventoLancePartida;
import com.example.futebolescqrs.commands.eventos.EventoNovaPartida;
import com.example.futebolescqrs.entidadesWrite.EventStorePartida;
import com.example.futebolescqrs.entidadesWrite.Partida;
import com.example.futebolescqrs.entidadesWrite.Time;
import com.example.futebolescqrs.repositoriosWrite.EventStorePartidaRepository;
import com.example.futebolescqrs.repositoriosWrite.TimeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    @Autowired
    private
    EventStorePartidaRepository repository;

    @Autowired
    private TimeRepository timeRepository;

    public long adicionaEventoNovaPartida(EventoNovaPartida evento) throws JsonProcessingException {

        Time timeCasa = timeRepository.findById(evento.getIdTimeCasa()).orElseThrow();
        Time timeRival = timeRepository.findById(evento.getIdTimeRival()).orElseThrow();

        Partida partida = new Partida(timeCasa, timeRival);

        EventStorePartida eventoPartida = new EventStorePartida(null, new ObjectMapper().writeValueAsString(partida),
                "INICIO_JOGO", 0);
        repository.save(eventoPartida);
        eventoPartida.setIdEntidade();
        repository.save(eventoPartida);

        return eventoPartida.getIdEntidade();
    }

    public void adicionaLancePartida(EventoLancePartida evento) throws JsonProcessingException {

        EventStorePartida eventoPartida = new EventStorePartida(evento.getIdEntidade(), new ObjectMapper().writeValueAsString(evento),
                evento.getTipoEvento(), evento.getMinuto());

        repository.save(eventoPartida);
    }

    public void adicionaFimPartida(EventoTempoPartida evento) throws JsonProcessingException {

        EventStorePartida eventoPartida = new EventStorePartida(evento.getIdEntidade(), new ObjectMapper().writeValueAsString(evento),
                evento.getTipoEvento(), evento.getMinuto());

        repository.save(eventoPartida);
    }
}
