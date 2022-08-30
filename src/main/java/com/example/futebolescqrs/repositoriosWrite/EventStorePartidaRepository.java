package com.example.futebolescqrs.repositoriosWrite;

import com.example.futebolescqrs.entidadesWrite.EventStorePartida;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventStorePartidaRepository extends JpaRepository<EventStorePartida, Long> {
    List<EventStorePartida> findByIdEntidade(Long idEntidade);

    @Query(value = "select * from event_store_partida where id_entidade = ? order by minuto", nativeQuery = true)
    List<EventStorePartida> findByIdEntidadeOrdenado(Long idEntidade);
}
