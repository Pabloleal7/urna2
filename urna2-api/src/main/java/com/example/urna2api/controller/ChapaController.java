package com.example.urna2api.controller;

import com.example.urna2api.entity.Chapa;
import com.example.urna2api.service.ChapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chapas")
public class ChapaController {

    @Autowired
    private ChapaService chapaService;

    @GetMapping("/{id}")
    public Chapa getChapa(@PathVariable UUID id) {
        return chapaService.getChapa(id);
    }

    @PostMapping
    public Chapa createChapa(@RequestBody Chapa chapa) {
        return chapaService.createChapa(chapa);
    }

    @PutMapping("/{id}")
    public Chapa updateChapa(@PathVariable UUID id, @RequestBody Chapa chapa) {
        return chapaService.updateChapa(id, chapa);
    }

    @DeleteMapping("/{id}")
    public void deleteChapa(@PathVariable UUID id) {
        chapaService.deleteChapa(id);
    }

    @GetMapping("/turma/{id}")
    public List<Chapa> getChapasByTurma(@PathVariable UUID id) {
        return chapaService.getChapasByTurma(id);
    }
}
