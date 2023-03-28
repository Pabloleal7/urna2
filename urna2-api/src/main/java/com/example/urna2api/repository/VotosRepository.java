package com.example.urna2api.repository;

import com.example.urna2api.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotosRepository extends JpaRepository<Voto, UUID> {
}
