package com.example.urna2api.service;

import com.example.urna2api.dto.ChapaInfo;
import com.example.urna2api.entity.Chapa;
import com.example.urna2api.entity.Turma;
import com.example.urna2api.entity.Voto;
import com.example.urna2api.repository.ChapaRepository;
import com.example.urna2api.repository.VotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VotosService {

    @Autowired
    private VotosRepository votosRepository;
    @Autowired
    private ChapaRepository chapaRepository;


    public List<Voto> getAllVotos() {
        return votosRepository.findAll();
    }

    public Voto saveVotos(String chapaId) {
        Voto voto = new Voto();
        Chapa chapa = chapaRepository.findById(UUID.fromString(chapaId)).orElseThrow(() -> new RuntimeException(
                "notfound"));
        voto.setChapa(chapa);
        return votosRepository.save(voto);
    }

    public Map<Turma, List<ChapaInfo>> getChapasInfo() {
        // Obtenha todos os votos (substitua pelo seu método de busca de votos)
        List<Voto> votos = votosRepository.findAll();

        // Crie um mapa com Chapa como chave e a quantidade de votos como valor
        Map<Chapa, Long> votosPorChapa = votos.stream()
                .collect(Collectors.groupingBy(Voto::getChapa, Collectors.counting()));

        // Crie um mapa com Turma como chave e uma lista de ChapaInfo como valor
        Map<Turma, List<ChapaInfo>> result = new HashMap<>();
        for (Map.Entry<Chapa, Long> entry : votosPorChapa.entrySet()) {
            Chapa chapa = entry.getKey();
            Turma turma = chapa.getTurma();
            long totalVotos = entry.getValue();

            ChapaInfo chapaInfo = new ChapaInfo(chapa, totalVotos);

            if (!result.containsKey(turma)) {
                result.put(turma, new ArrayList<>());
            }

            result.get(turma).add(chapaInfo);
        }

        return result;
    }
}
