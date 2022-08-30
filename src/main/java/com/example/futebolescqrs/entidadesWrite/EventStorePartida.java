package com.example.futebolescqrs.entidadesWrite;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EventStorePartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEntidade;

    @Column(columnDefinition = "TEXT")
    private String dadosEvento;

    private String tipoEvento;

    private int minuto;

    private LocalDateTime horaEvento = LocalDateTime.now();

    @Deprecated
    public EventStorePartida() {
    }

    public EventStorePartida(Long idEntidade, String dadosEvento, String tipoEvento, int minuto) {
        this.idEntidade = idEntidade;
        this.dadosEvento = dadosEvento;
        this.tipoEvento = tipoEvento;
        this.minuto = minuto;
    }

    public Long getId() {
        return id;
    }

    public Long getIdEntidade() {
        return idEntidade;
    }

    public String getDadosEvento() {
        return dadosEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public int getMinuto() {
        return minuto;
    }

    public LocalDateTime getHoraEvento() {
        return horaEvento;
    }

    public void setIdEntidade() {
        idEntidade = id;
    }
}
