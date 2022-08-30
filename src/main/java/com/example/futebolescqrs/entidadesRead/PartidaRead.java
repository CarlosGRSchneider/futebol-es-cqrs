package com.example.futebolescqrs.entidadesRead;

import com.example.futebolescqrs.entidadesWrite.Time;

public class PartidaRead {

    private Long id;

    private Time timeCasa;

    private Time timeRival;

    private int golsTimeCasa;

    private int golsTimeRival;

    private int cartaoAmareloTimeCasa;

    private int cartaoAmareloTimeRival;

    private int cartaoVermelhoTimeCasa;

    private int cartaoVermelhoTimeRival;

    private int penaltiTimeCasa;

    private int penaltiTimeRival;

    @Deprecated
    public PartidaRead() {}

    public PartidaRead(Long id, Time timeCasa, Time timeRival, int golsTimeCasa, int golsTimeRival, int cartaoAmareloTimeCasa, int cartaoAmareloTimeRival, int cartaoVermelhoTimeCasa, int cartaoVermelhoTimeRival, int penaltiTimeCasa, int penaltiTimeRival) {
        this.id = id;
        this.timeCasa = timeCasa;
        this.timeRival = timeRival;
        this.golsTimeCasa = golsTimeCasa;
        this.golsTimeRival = golsTimeRival;
        this.cartaoAmareloTimeCasa = cartaoAmareloTimeCasa;
        this.cartaoAmareloTimeRival = cartaoAmareloTimeRival;
        this.cartaoVermelhoTimeCasa = cartaoVermelhoTimeCasa;
        this.cartaoVermelhoTimeRival = cartaoVermelhoTimeRival;
        this.penaltiTimeCasa = penaltiTimeCasa;
        this.penaltiTimeRival = penaltiTimeRival;
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

    public void addGolsTimeCasa() {
        this.golsTimeCasa += 1;
    }

    public void removeGolsTimeCasa() {
        this.golsTimeCasa -= 1;
    }

    public void addGolsTimeRival() {
        this.golsTimeRival += 1;
    }

    public void removeGolsTimeRival() {
        this.golsTimeRival -= 1;
    }

    public void addCartaoAmareloTimeCasa() {
        this.cartaoAmareloTimeCasa += 1;
    }

    public void removeCartaoAmareloTimeCasa() {
        this.cartaoAmareloTimeCasa -= 1;
    }

    public void addCartaoAmareloTimeRival() {
        this.cartaoAmareloTimeRival += 1;
    }

    public void removeCartaoAmareloTimeRival() {
        this.cartaoAmareloTimeRival -= 1;
    }

    public void addCartaoVermelhoTimeCasa() {
        this.cartaoVermelhoTimeCasa += 1;
    }

    public void removeCartaoVermelhoTimeCasa() {
        this.cartaoVermelhoTimeCasa -= 1;
    }

    public void addCartaoVermelhoTimeRival() {
        this.cartaoVermelhoTimeRival += 1;
    }

    public void removeCartaoVermelhoTimeRival() {
        this.cartaoVermelhoTimeRival -= 1;
    }

    public void addPenaltiTimeCasa() {
        this.penaltiTimeCasa += 1;
    }

    public void removePenaltiTimeCasa() {
        this.penaltiTimeCasa -= 1;
    }

    public void addPenaltiTimeRival() {
        this.penaltiTimeRival += 1;
    }

    public void removePenaltiTimeRival() {
        this.penaltiTimeRival -= 1;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
