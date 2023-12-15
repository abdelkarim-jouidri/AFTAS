package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Member.CreateMemberDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MemberService {
    List<MemberDTO> findAll();
    MemberDTO createMember(CreateMemberDTO memberDTO);
    RankingDTO registerMemberForCompetition(Integer memberNum, String competitionCode);
}
