package com.example.futebolescqrs.queries;

import com.example.futebolescqrs.entidadesRead.JogadorRead;
import com.example.futebolescqrs.repositoriosRead.JogadorReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogadores/read")
public class JogadorQuery {

    @Autowired
    private JogadorReadRepository repository;

    @GetMapping
    public ResponseEntity<List<JogadorRead>> findAll() {
        List<JogadorRead> jogadores = repository.findAll();

        return ResponseEntity.ok().body(jogadores);
    }

    @GetMapping("/{id}/id")
    public ResponseEntity<JogadorRead> findJogadorById(@PathVariable Long id) {
        Optional<JogadorRead> jogador = repository.findById(id);

        if (jogador.isPresent()) {
            return ResponseEntity.ok().body(jogador.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{nome}/nome")
    public ResponseEntity<JogadorRead> findJogadorByNome(@PathVariable String nome) {
        Optional<JogadorRead> jogador = repository.findByNome(nome);

        if (jogador.isPresent()) {
            return ResponseEntity.ok().body(jogador.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<List<JogadorRead>> getEstatisticasDeGol() {

        List<JogadorRead> jogadores = repository.findByGolsMarcadosDesc();

        return ResponseEntity.ok().body(jogadores);
    }
}

