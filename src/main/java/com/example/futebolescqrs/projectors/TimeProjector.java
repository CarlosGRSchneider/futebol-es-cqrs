package com.example.futebolescqrs.projectors;

import com.example.futebolescqrs.entidadesRead.JogadorRead;
import com.example.futebolescqrs.entidadesRead.TimeRead;
import com.example.futebolescqrs.entidadesWrite.Time;
import com.example.futebolescqrs.repositoriosRead.JogadorReadRepository;
import com.example.futebolescqrs.repositoriosRead.TimeReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeProjector implements Projector<Time> {

    @Autowired
    private TimeReadRepository repository;

    @Autowired
    private JogadorReadRepository jogadorRepository;

    @Override
    public void project(Time time) {

        List<JogadorRead> jogadores = time.getJogadores().stream()
                .map(jogador -> jogadorRepository.findById(jogador.getId()).get())
                .collect(Collectors.toList());

        TimeRead timeRead = new TimeRead(time.getId(), time.getNome(), jogadores);
        repository.save(timeRead);

    }
}
