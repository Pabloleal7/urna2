package com.example.urna2api.controller;

import com.example.urna2api.dto.ChapaInfo;
import com.example.urna2api.entity.Turma;
import com.example.urna2api.entity.Voto;
import com.example.urna2api.service.VotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/votos")
public class VotosController {

    @Autowired
    private VotosService votosService;

    @GetMapping("/all")
    public List<Voto> getAllVotos() {
        return votosService.getAllVotos();
    }

    @PostMapping("/{chapaId}")
    public Voto saveVotos(@PathVariable String chapaId) {
        return votosService.saveVotos(chapaId);
    }

    @GetMapping()
    public Map<Turma, List<ChapaInfo>> getAllVotosMap(){
        return votosService.getChapasInfo();
    }
}