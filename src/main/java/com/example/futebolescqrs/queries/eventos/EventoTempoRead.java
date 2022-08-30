package com.example.futebolescqrs.queries.eventos;

public class EventoTempoRead implements EventoRead{

    private String tipoEvento;
    private int minuto;

    public EventoTempoRead(String tipoEvento, int minuto) {
        this.tipoEvento = tipoEvento;
        this.minuto = minuto;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public int getMinuto() {
        return minuto;
    }
}
