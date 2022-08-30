package com.example.futebolescqrs.entidadesWrite;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Partida {

    private Long id;

    private Time timeCasa;

    private Time timeRival;

    private int golsTimeCasa = 0;

    private int golsTimeRival = 0;

    private int cartaoAmareloTimeCasa = 0;

    private int cartaoAmareloTimeRival = 0;

    private int cartaoVermelhoTimeCasa = 0;

    private int cartaoVermelhoTimeRival = 0;

    private int penaltiTimeCasa = 0;

    private int penaltiTimeRival = 0;


    @Deprecated
    public Partida() {
    }

    public Partida(Time timeCasa, Time timeRival) {
        this.timeCasa = timeCasa;
        this.timeRival = timeRival;
    }

    public Long getId() {
        return id;
    }

    public Time getTimeCasa() {
        return timeCasa;
    }

    public Time getTimeRival() {
        return timeRival;
    }

    public int getGolsTimeCasa() {
        return golsTimeCasa;
    }

    public int getGolsTimeRival() {
        return golsTimeRival;
    }

    public int getCartaoAmareloTimeCasa() {
        return cartaoAmareloTimeCasa;
    }

    public int getCartaoAmareloTimeRival() {
        return cartaoAmareloTimeRival;
    }

    public int getCartaoVermelhoTimeCasa() {
        return cartaoVermelhoTimeCasa;
    }

    public int getCartaoVermelhoTimeRival() {
        return cartaoVermelhoTimeRival;
    }

    public int getPenaltiTimeCasa() {
        return penaltiTimeCasa;
    }

    public int getPenaltiTimeRival() {
        return penaltiTimeRival;
    }
}
