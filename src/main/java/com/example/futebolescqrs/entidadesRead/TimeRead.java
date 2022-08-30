package com.example.futebolescqrs.entidadesRead;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TimeRead {

    @Id
    private Long id;

    private String nome;

    @OneToMany
    private List<JogadorRead> jogadores = new ArrayList<>();

    @Deprecated
    public TimeRead() {
    }

    public TimeRead(Long id, String nome, List<JogadorRead> jogadores) {
        this.id = id;
        this.nome = nome;
        this.jogadores = jogadores;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<JogadorRead> getJogadores() {
        return jogadores;
    }
}
