package com.example.urna2api.entity;

import com.example.urna2api.enums.Turno;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
@Entity
@Getter@Setter
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nomeDaTurma;

    private String sala;
    @Enumerated(EnumType.STRING)
    private Turno turno;
    @JsonManagedReference
    @OneToMany(mappedBy = "turma")
    private Set<Chapa> chapaSet;

    public Turma turmaSemChapas() {
        Turma turma = new Turma();
        turma.setId(id);
        turma.setNomeDaTurma(nomeDaTurma);
        turma.setSala(sala);
        turma.setTurno(turno);
        return turma;
    }


    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(turmaSemChapas());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
