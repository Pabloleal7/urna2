package com.example.urna2api.dto;

import com.example.urna2api.entity.Chapa;
import lombok.Data;

@Data
public class ChapaInfo {
    private Chapa chapa;
    private long totalVotos;

    public ChapaInfo(Chapa chapa, long totalVotos) {
        this.chapa = chapa;
        this.totalVotos = totalVotos;
    }

    // Getter e Setter
}