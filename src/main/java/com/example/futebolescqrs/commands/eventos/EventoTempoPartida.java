package com.example.futebolescqrs.commands.eventos;

public class EventoTempoPartida {

    private Long idEntidade;

    private String tipoEvento;

    private int minuto;

    public Long getIdEntidade() {
        return idEntidade;
    }

    public int getMinuto() {
        return minuto;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }
}
