package com.accenture.order.controller;

import brave.Tracer;
import com.accenture.magicwand.repository.MagicWandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MagicWandApi")
public class MagicWandController {

    private final MagicWandRepository repo;

    Tracer tracer;

    public MagicWandController(MagicWandRepository repo, Tracer tracer) {
        this.repo = repo;
        this.tracer = tracer;
    }

    @GetMapping("/getInfo/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id) {
        System.out.println(tracer.currentSpan().context().traceIdString());
        return ResponseEntity.ok(repo.findById(id).get());
    }
}
