package com.example.futebolescqrs.commands.eventos;

public class EventoNovaPartida {

    private long idTimeCasa;
    private long idTimeRival;

    public long getIdTimeCasa() {
        return idTimeCasa;
    }

    public long getIdTimeRival() {
        return idTimeRival;
    }
}
