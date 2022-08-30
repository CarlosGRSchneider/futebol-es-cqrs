package com.example.futebolescqrs.commands.eventos;

import com.example.futebolescqrs.entidadesWrite.Partida;

public class EventoLancePartida {

    private Long idEntidade;
    private String timeOcorrencia;
    private String jogadorOcorrencia;
    private String tipoEvento;
    private int minuto;

    public Long getIdEntidade() {
        return idEntidade;
    }

    public String getTimeOcorrencia() {
        return timeOcorrencia;
    }

    public String getJogadorOcorrencia() {
        return jogadorOcorrencia;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public int getMinuto() {
        return minuto;
    }
}
