package com.example.futebolescqrs.queries.eventos;

public class EventoLanceRead implements EventoRead {

    private String tipoEvento;
    private String time;
    private String jogador;

    private int minuto;

    public EventoLanceRead(String tipoEvento, String time, String jogador, int minuto) {
        this.tipoEvento = tipoEvento;
        this.time = time;
        this.jogador = jogador;
        this.minuto = minuto;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public String getTime() {
        return time;
    }

    public String getJogador() {
        return jogador;
    }

    public int getMinuto() {
        return minuto;
    }
}
