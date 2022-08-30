package com.example.futebolescqrs.queries.eventos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class EventStorePartidaRead {

    @Id
    private Long id;

    private Long idEntidade;

    @Column(columnDefinition = "TEXT")
    private String dadosEvento;

    private String tipoEvento;

    private int minuto;

    private LocalDateTime horaEvento;

    @Deprecated
    public EventStorePartidaRead() {
    }

    public EventStorePartidaRead(Long id, Long idEntidade, String dadosEvento, String tipoEvento, int minuto, LocalDateTime horaEvento) {
        this.id = id;
        this.idEntidade = idEntidade;
        this.dadosEvento = dadosEvento;
        this.tipoEvento = tipoEvento;
        this.minuto = minuto;
        this.horaEvento = horaEvento;
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

