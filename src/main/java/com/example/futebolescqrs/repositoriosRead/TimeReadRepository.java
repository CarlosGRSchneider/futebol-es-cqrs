package com.example.futebolescqrs.repositoriosRead;

import com.example.futebolescqrs.entidadesRead.TimeRead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeReadRepository extends JpaRepository<TimeRead, Long> {
}
