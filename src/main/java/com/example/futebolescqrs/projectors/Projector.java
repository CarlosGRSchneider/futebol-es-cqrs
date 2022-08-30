package com.example.futebolescqrs.projectors;

import org.springframework.stereotype.Service;

@Service
public interface Projector<T> {

    void project(T t);
}
