package com.example.urna2api.service;

import com.example.urna2api.entity.Chapa;
import com.example.urna2api.error.ResourceNotFoundException;
import com.example.urna2api.repository.ChapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChapaService {

    @Autowired
    private ChapaRepository chapaRepository;

    public Chapa getChapa(UUID id) {
        return chapaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chapa", "id", id));
    }

    public Chapa createChapa(Chapa chapa) {
        return chapaRepository.save(chapa);
    }

    public Chapa updateChapa(UUID id, Chapa chapa) {
        Chapa existingChapa = getChapa(id);
        existingChapa.setLider(chapa.getLider());
        existingChapa.setViceLider(chapa.getViceLider());
        existingChapa.setTurma(chapa.getTurma());
        return chapaRepository.save(existingChapa);
    }

    public void deleteChapa(UUID id) {
        Chapa chapa = getChapa(id);
        chapaRepository.delete(chapa);
    }

    public List<Chapa> getChapasByTurma(UUID turmaId) {
        return chapaRepository.findByTurmaId(turmaId);
    }
}
