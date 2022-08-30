package com.example.futebolescqrs.projectors;

import com.example.futebolescqrs.commands.eventos.EventoLancePartida;
import com.example.futebolescqrs.queries.eventos.EventStorePartidaRead;
import com.example.futebolescqrs.entidadesRead.JogadorRead;
import com.example.futebolescqrs.entidadesWrite.EventStorePartida;
import com.example.futebolescqrs.repositoriosRead.EventStorePartidaReadRepository;
import com.example.futebolescqrs.repositoriosRead.JogadorReadRepository;
import com.example.futebolescqrs.repositoriosWrite.EventStorePartidaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidaEventStoreProjector implements Projector<Long> {

    @Autowired
    private EventStorePartidaRepository repository;

    @Autowired
    private EventStorePartidaReadRepository readRepository;

    @Autowired
    private JogadorReadRepository jogadorRepository;

    @Override
    public void project(Long event) {

        List<EventStorePartida> eventos = repository.findByIdEntidadeOrdenado(event);
        List<EventStorePartidaRead> eventosRead = new ArrayList<>();

        for (int i = 0; i < eventos.size(); i++) {
            EventStorePartida evento = eventos.get(i);
            if (evento.getTipoEvento().equals("GOL_MARCADO") && !eventos.get(i + 1).getTipoEvento().equals("DESFAZ_GOL_MARCADO")) {
                marcaGolJogador(evento.getDadosEvento());
            }

            EventStorePartidaRead eventoRead = new EventStorePartidaRead(evento.getId(), evento.getIdEntidade(), evento.getDadosEvento(), evento.getTipoEvento(), evento.getMinuto(), evento.getHoraEvento());
            eventosRead.add(eventoRead);
        }

        readRepository.saveAll(eventosRead);
    }

    private void marcaGolJogador(String dadosEvento) {

       try {
           EventoLancePartida lancePartida = new ObjectMapper().readValue(dadosEvento, EventoLancePartida.class);

           JogadorRead jogadorRead = jogadorRepository.findByNome(lancePartida.getJogadorOcorrencia()).get();

           jogadorRead.adicionaGols(1);
           jogadorRepository.save(jogadorRead);

       } catch (JsonProcessingException e) {
           e.printStackTrace();
       }
    }
}
