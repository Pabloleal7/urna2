package com.example.urna2api.repository;

import com.example.urna2api.entity.Chapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChapaRepository extends JpaRepository<Chapa, UUID> {
    List<Chapa> findByTurmaId(UUID turmaId);
}
