package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.ViewCompetitionDTO;
import com.example.aftas.Entities.DTOs.Member.CreateMemberDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Entities.DTOs.Member.ViewMemberDTO;
import com.example.aftas.Entities.DTOs.Ranking.CreateRankingDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Member;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Entities.Models.RankingKey;
import com.example.aftas.Exceptions.CompetitionRegistrationException;
import com.example.aftas.Exceptions.MemberAlreadyRegisteredForCompetitionException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Repositories.MemberRepository;
import com.example.aftas.Repositories.RankingRepository;
import com.example.aftas.Services.CompetitionService;
import com.example.aftas.Services.MemberService;
import com.example.aftas.Services.RankingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("memberServiceImpl")
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final CompetitionService competitionService;
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;
    private final RankingService rankingService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<ViewMemberDTO> findAll() {
        return memberRepository.
                findAll().
                stream().
                map(member -> modelMapper.map(member, ViewMemberDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MemberDTO createMember(CreateMemberDTO memberDTO) {
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        Member member = modelMapper.map(memberDTO, Member.class);
        return modelMapper.map(memberRepository.save(member), MemberDTO.class);
    }

    @Override
    public RankingDTO registerMemberForCompetition(Integer memberNum, String competitionCode) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(competitionCode);
        if (optionalCompetition.isEmpty()) throw new NoSuchElementException("No such competition by this code : "+competitionCode);
        Competition competition = optionalCompetition.get();
        if(!competitionService.isRegistrationOpenForMemberService(competition)){
            throw new CompetitionRegistrationException("Registration is no longer allowed for this competition.");
        }

        RankingKey rankingKey = RankingKey.builder().num(memberNum).code(competitionCode).build();
        if(rankingRepository.findById(rankingKey).isPresent()) {
            throw new MemberAlreadyRegisteredForCompetitionException();
        }
        RankingDTO builtRanking = RankingDTO.builder().id(rankingKey).build();
        RankingDTO map = modelMapper.map(rankingService.saveRanking(builtRanking), RankingDTO.class);
        return map;
    }



}
