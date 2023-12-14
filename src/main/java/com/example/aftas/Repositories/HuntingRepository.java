package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Fish;
import com.example.aftas.Entities.Models.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
}
