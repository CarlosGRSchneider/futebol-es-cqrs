package com.example.futebolescqrs.service;

import com.example.futebolescqrs.commands.eventos.EventoLancePartida;
import com.example.futebolescqrs.entidadesRead.PartidaRead;
import com.example.futebolescqrs.queries.eventos.EventStorePartidaRead;
import com.example.futebolescqrs.queries.eventos.EventoLanceRead;
import com.example.futebolescqrs.queries.eventos.EventoRead;
import com.example.futebolescqrs.queries.eventos.EventoTempoRead;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PartidaReadService {

    private final List<String> EVENTOS_TEMPO = Arrays.asList("INICIO_JOGO", "FIM_PRIMEIRO_TEMPO", "INICIO_SEGUNDO_TEMPO", "FIM_JOGO");

    public PartidaRead montaPartida(List<EventStorePartidaRead> eventosPartida) {

        try {
            PartidaRead partidaRead = montaPartidaRead(eventosPartida.get(0).getDadosEvento());
            partidaRead.setId(eventosPartida.get(0).getId());

            for (int i = 1; i < eventosPartida.size(); i++) {
                EventStorePartidaRead evento = eventosPartida.get(i);
                partidaRead = desenvolveEventosPartida(partidaRead, evento);
            }


            return partidaRead;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private PartidaRead montaPartidaRead(String dadosEvento) throws JsonProcessingException {

        return new ObjectMapper().readValue(dadosEvento, PartidaRead.class);
    }

    private PartidaRead desenvolveEventosPartida(PartidaRead partidaRead, EventStorePartidaRead evento) throws JsonProcessingException {

        switch (evento.getTipoEvento()) {
            case "GOL_MARCADO":
                EventoLancePartida lancePartidaGol = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaGol.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.addGolsTimeCasa();
                } else {
                    partidaRead.addGolsTimeRival();
                }
                return partidaRead;
            case "DESFAZ_GOL_MARCADO":
                EventoLancePartida lancePartidaDesfazGol = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaDesfazGol.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.removeGolsTimeCasa();
                } else {
                    partidaRead.removeGolsTimeRival();
                }
                return partidaRead;
            case "CARTAO_AMARELO_MARCADO":
                EventoLancePartida lancePartidaCartaoAmarelo = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaCartaoAmarelo.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.addCartaoAmareloTimeCasa();
                } else {
                    partidaRead.addCartaoAmareloTimeRival();
                }
                return partidaRead;
            case "DESFAZ_CARTAO_AMARELO_MARCADO":
                EventoLancePartida lancePartidaDesfazCartaoAmarelo = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaDesfazCartaoAmarelo.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.removeCartaoAmareloTimeCasa();
                } else {
                    partidaRead.removeCartaoAmareloTimeRival();
                }
                return partidaRead;
            case "CARTAO_VERMELHO_MARCADO":
                EventoLancePartida lancePartidaCartaoVermelho = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaCartaoVermelho.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.addCartaoVermelhoTimeCasa();
                } else {
                    partidaRead.addCartaoVermelhoTimeRival();
                }
                return partidaRead;
            case "DESFAZ_CARTAO_VERMELHO_MARCADO":
                EventoLancePartida lancePartidaDesfazCartaoVermelho = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaDesfazCartaoVermelho.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.removeCartaoVermelhoTimeCasa();
                } else {
                    partidaRead.removeCartaoVermelhoTimeRival();
                }
                return partidaRead;
            case "PENALTI_MARCADO":
                EventoLancePartida lancePartidaPenalti = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaPenalti.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.addPenaltiTimeCasa();
                } else {
                    partidaRead.addPenaltiTimeRival();
                }
                return partidaRead;
            case "DESFAZ_PENALTI_MARCADO":
                EventoLancePartida lancePartidaDesfazPenalti = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
                if (lancePartidaDesfazPenalti.getTimeOcorrencia().equals(partidaRead.getTimeCasa().getNome())) {
                    partidaRead.removePenaltiTimeCasa();
                } else {
                    partidaRead.removePenaltiTimeRival();
                }
                return partidaRead;
            default:
                return partidaRead;
        }
    }


    public List<EventoRead> montaEventosPartida(List<EventStorePartidaRead> eventosPartida) {
        List<EventoRead> eventos = new ArrayList<>();

        try {
            for (EventStorePartidaRead evento : eventosPartida) {
                eventos.add(resolveEvento(evento));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return eventos;
    }


    public List<EventoRead> montaPrimeiroTempo(List<EventStorePartidaRead> eventosPartida) {

        List<EventStorePartidaRead> eventosPrimeiroTempo = new ArrayList<>();
        for (EventStorePartidaRead eventoPartida : eventosPartida) {
            eventosPrimeiroTempo.add(eventoPartida);
            if (eventoPartida.getTipoEvento().equals("FIM_PRIMEIRO_TEMPO")) {
                break;
            }
        }

        List<EventoRead> eventos = new ArrayList<>();

        try {
            for (EventStorePartidaRead evento : eventosPrimeiroTempo) {
                eventos.add(resolveEvento(evento));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    public List<EventoRead> montaSegundoTempo(List<EventStorePartidaRead> eventosPartida) {

        boolean segundoTempo = false;

        List<EventStorePartidaRead> eventosSegundoTempo = new ArrayList<>();
        for (EventStorePartidaRead eventoPartida : eventosPartida) {
            if (eventoPartida.getTipoEvento().equals("INICIO_SEGUNDO_TEMPO")) {
                segundoTempo = true;
            }
            if (segundoTempo) {
                eventosSegundoTempo.add(eventoPartida);
            }
        }

        List<EventoRead> eventos = new ArrayList<>();

        try {
            for (EventStorePartidaRead evento : eventosSegundoTempo) {
                eventos.add(resolveEvento(evento));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    private EventoRead resolveEvento(EventStorePartidaRead evento) throws JsonProcessingException {
        if (EVENTOS_TEMPO.contains(evento.getTipoEvento())) {
            return new EventoTempoRead(evento.getTipoEvento(), evento.getMinuto());
        } else {
            EventoLancePartida eventoLancePartida = new ObjectMapper().readValue(evento.getDadosEvento(), EventoLancePartida.class);
            return new EventoLanceRead(evento.getTipoEvento(), eventoLancePartida.getTimeOcorrencia(), eventoLancePartida.getJogadorOcorrencia(), evento.getMinuto());
        }
    }
}
