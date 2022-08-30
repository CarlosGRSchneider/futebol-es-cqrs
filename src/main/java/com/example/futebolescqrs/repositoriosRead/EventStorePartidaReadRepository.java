package com.example.futebolescqrs.repositoriosRead;

import com.example.futebolescqrs.queries.eventos.EventStorePartidaRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventStorePartidaReadRepository extends JpaRepository<EventStorePartidaRead, Long> {

    @Query(value = "select * from event_store_partida_read where id_entidade = ? order by minuto", nativeQuery = true)
    List<EventStorePartidaRead> findByIdEntidadeOrdenado(Long idEntidade);
}
