package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Fish;
import com.example.aftas.Entities.Models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LevelRepository extends JpaRepository<Level, Integer> {
}
