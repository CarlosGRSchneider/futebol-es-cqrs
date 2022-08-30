package com.example.futebolescqrs.repositoriosWrite;

import com.example.futebolescqrs.entidadesWrite.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
