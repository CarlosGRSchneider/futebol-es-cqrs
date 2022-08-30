package com.example.futebolescqrs.queries;

import com.example.futebolescqrs.entidadesRead.TimeRead;
import com.example.futebolescqrs.repositoriosRead.TimeReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/times/read")
public class TimeQuery {

    @Autowired
    private TimeReadRepository repository;

    @GetMapping
    public ResponseEntity<List<TimeRead>> getAll() {
        List<TimeRead> times = repository.findAll();

        return ResponseEntity.ok().body(times);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeRead> getTimeById(@PathVariable Long id) {

        Optional<TimeRead> time = repository.findById(id);

        if (time.isPresent()) {
            return ResponseEntity.ok().body(time.get());
        }
        return ResponseEntity.notFound().build();
    }
}

