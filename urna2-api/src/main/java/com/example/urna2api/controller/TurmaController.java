package com.example.urna2api.controller;

import com.example.urna2api.entity.Chapa;
import com.example.urna2api.entity.Turma;
import com.example.urna2api.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id}")
    public Turma getTurma(@PathVariable UUID id) {
        return turmaService.getTurma(id);
    }

    @PostMapping
    public Turma createTurma(@RequestBody Turma turma) {
        return turmaService.createTurma(turma);
    }

    @PutMapping("/{id}")
    public Turma updateTurma(@PathVariable UUID id, @RequestBody Turma turma) {
        return turmaService.updateTurma(id, turma);
    }

    @DeleteMapping("/{id}")
    public void deleteTurma(@PathVariable UUID id) {
        turmaService.deleteTurma(id);
    }

    @GetMapping("/{id}/chapas")
    public Set<Chapa> getChapasByTurma(@PathVariable UUID id) {
        return turmaService.getChapasByTurma(id);
    }

    @GetMapping()
    private List<Turma> getAllTurmas(){
        return turmaService.findAllTurmas();
    }
}
