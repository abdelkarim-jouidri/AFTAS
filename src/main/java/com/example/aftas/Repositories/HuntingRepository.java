package com.example.aftas.Repositories;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Fish.FishDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Fish;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Entities.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findByCompetitionCodeAndMemberNumAndFishName(String code, Integer num, String name);
}
