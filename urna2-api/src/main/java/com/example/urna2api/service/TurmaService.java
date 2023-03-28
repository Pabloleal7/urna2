package com.example.urna2api.service;

import com.example.urna2api.entity.Chapa;
import com.example.urna2api.entity.Turma;
import com.example.urna2api.error.ResourceNotFoundException;
import com.example.urna2api.repository.ChapaRepository;
import com.example.urna2api.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ChapaRepository chapaRepository;

    public Turma getTurma(UUID id) {
        return turmaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Turma", "id", id));
    }

    public Turma createTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma updateTurma(UUID id, Turma turma) {
        Turma existingTurma = getTurma(id);
        existingTurma.setNomeDaTurma(turma.getNomeDaTurma());
        existingTurma.setSala(turma.getSala());
        existingTurma.setTurno(turma.getTurno());
        return turmaRepository.save(existingTurma);
    }

    public void deleteTurma(UUID id) {
        Turma turma = getTurma(id);
        turmaRepository.delete(turma);
    }

    public Set<Chapa> getChapasByTurma(UUID turmaId) {
        Turma turma = getTurma(turmaId);
        return turma.getChapaSet();
    }

    public Chapa addChapa(UUID turmaId, Chapa chapa) {
        Turma turma = getTurma(turmaId);
        chapa.setTurma(turma);
        return chapaRepository.save(chapa);
    }

    public List<Turma> findAllTurmas() {
        return turmaRepository.findAll();
    }
}
