package com.example.futebolescqrs.queries;

import com.example.futebolescqrs.queries.eventos.EventStorePartidaRead;
import com.example.futebolescqrs.queries.eventos.EventoRead;
import com.example.futebolescqrs.entidadesRead.PartidaRead;
import com.example.futebolescqrs.repositoriosRead.EventStorePartidaReadRepository;
import com.example.futebolescqrs.service.PartidaReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partidas/read")
public class PartidaQuery {

    @Autowired
    private EventStorePartidaReadRepository repository;

    @Autowired
    private PartidaReadService service;

    @GetMapping("/{idEntidade}")
    public ResponseEntity<PartidaRead> getPartidaPorId(@PathVariable Long idEntidade) {
        List<EventStorePartidaRead> eventosPartida = repository.findByIdEntidadeOrdenado(idEntidade);

        if (!eventosPartida.isEmpty()) {
            PartidaRead partidaRead = service.montaPartida(eventosPartida);

            return ResponseEntity.ok().body(partidaRead);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idEntidade}/eventos")
    public  ResponseEntity<List<EventoRead>> getAllEventosPartida(@PathVariable Long idEntidade) {
        List<EventStorePartidaRead> eventosPartida = repository.findByIdEntidadeOrdenado(idEntidade);

        if (!eventosPartida.isEmpty()) {
            List<EventoRead> eventosRead = service.montaEventosPartida(eventosPartida);

            return ResponseEntity.ok().body(eventosRead);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idEntidade}/primeiro-tempo")
    public  ResponseEntity<List<EventoRead>> getPrimeiroTempoPartida(@PathVariable Long idEntidade) {
        List<EventStorePartidaRead> eventosPartida = repository.findByIdEntidadeOrdenado(idEntidade);

        if (!eventosPartida.isEmpty()) {
            List<EventoRead> eventosRead = service.montaPrimeiroTempo(eventosPartida);

            return ResponseEntity.ok().body(eventosRead);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idEntidade}/segundo-tempo")
    public  ResponseEntity<List<EventoRead>> getSegundoTempoPartida(@PathVariable Long idEntidade) {
        List<EventStorePartidaRead> eventosPartida = repository.findByIdEntidadeOrdenado(idEntidade);

        if (!eventosPartida.isEmpty()) {
            List<EventoRead> eventosRead = service.montaSegundoTempo(eventosPartida);

            return ResponseEntity.ok().body(eventosRead);
        }

        return ResponseEntity.notFound().build();
    }

}
