package com.example.futebolescqrs.repositoriosWrite;

import com.example.futebolescqrs.entidadesWrite.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

}
