package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, String> {
}
