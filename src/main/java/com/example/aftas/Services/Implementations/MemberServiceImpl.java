package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Member.CreateMemberDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Entities.DTOs.Ranking.CreateRankingDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Member;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;
import com.example.aftas.Exceptions.CompetitionRegistrationException;
import com.example.aftas.Repositories.MemberRepository;
import com.example.aftas.Services.CompetitionService;
import com.example.aftas.Services.MemberService;
import com.example.aftas.Services.RankingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("memberServiceImpl")
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final CompetitionService competitionService;
    private final RankingService rankingService;
    private final ModelMapper modelMapper;
    @Override
    public List<MemberDTO> findAll() {
        return memberRepository.
                findAll().
                stream().
                map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MemberDTO createMember(CreateMemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        return modelMapper.map(memberRepository.save(member), MemberDTO.class);
    }

    @Override
    public RankingDTO registerMemberForCompetition(Integer memberNum, String competitionCode) {
        CompetitionDTO competitionByCode = competitionService.findCompetitionByCode(competitionCode);
        if(!competitionService.isRegistrationOpen(competitionByCode)){
            throw new CompetitionRegistrationException("Registration is no longer allowed for this competition.");
        }
        RankingKey rankingKey = RankingKey.builder().num(memberNum).code(competitionCode).build();
        RankingDTO builtRanking = RankingDTO.builder().id(rankingKey).build();

        return modelMapper.map(rankingService.saveRanking(builtRanking), RankingDTO.class);
    }
}
