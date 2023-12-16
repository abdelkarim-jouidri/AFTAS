package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Hunting.CreateHuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.ViewHuntingDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Entities.Models.Ranking;
import com.example.aftas.Exceptions.CannotStoreHuntingException;
import com.example.aftas.Exceptions.CompetitionHasExpiredException;
import com.example.aftas.Exceptions.CompetitionHasNotStartedException;
import com.example.aftas.Exceptions.CompetitionIClosedException;
import com.example.aftas.Repositories.*;
import com.example.aftas.Services.CompetitionService;
import com.example.aftas.Services.HuntingService;
import com.example.aftas.Services.RankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final RankingService rankingService;
    private final MemberRepository memberRepository;
    private final FishRepository fishRepository;
    private final CompetitionRepository competitionRepository;
    @Qualifier("competitionServiceImpl")
    private final CompetitionService competitionService;
    private final ModelMapper modelMapper;
    @Override
    public ViewHuntingDTO saveHunting(CreateHuntingDTO huntingDTO) {
        String code = huntingDTO.getCompetition().getCode();
        Integer num = huntingDTO.getMember().getNum();
        String name = huntingDTO.getFish().getName();
        if(fishRepository.findById(huntingDTO.getFish().getName()).isEmpty()){
            throw new NoSuchElementException("Fish named : "+huntingDTO.getFish().getName()+" doesn't exist");
        }
        if(memberRepository.findById(huntingDTO.getMember().getNum()).isEmpty()){
            throw new NoSuchElementException("No such Member with this num : "+huntingDTO.getMember().getNum());
        }
        if(competitionRepository.findById(huntingDTO.getCompetition().getCode()).isEmpty()){
            throw new NoSuchElementException("No such competition with this code : "+huntingDTO.getCompetition().getCode());
        }else {
            Competition competition = competitionRepository.findById(huntingDTO.getCompetition().getCode()).get();
            if(competitionService.isCompetitionExpired(competition)){
                throw new CompetitionHasExpiredException(competition.getDate());
            }
            if(competitionService.isCompetitionUpcoming(competition)){
                throw new CompetitionHasNotStartedException(competition.getDate());
            }
            if ( !competitionService.isCompetitionOngoing(competition)){
                throw new CompetitionIClosedException(competition.getDate());
            }
        }
        Optional<Hunting> optionalExistingHunting = huntingRepository.findByCompetitionCodeAndMemberNumAndFishName(code, num, name);
        if (optionalExistingHunting.isPresent()){
            Hunting hunting = optionalExistingHunting.get();
            Double caughtFishWeight = huntingDTO.getCaughtFishWeight();
            int numberOfFishes = hunting.getNumberOfFishes();
            if(canStoreHuntingRecord(hunting, caughtFishWeight)){
                hunting.setNumberOfFishes(numberOfFishes + huntingDTO.getNumberOfFishes());
                Hunting savedHunting = huntingRepository.save(hunting);
                Ranking ranking = rankingService.updateScore(savedHunting);
                System.out.println("****"+ranking);
                return modelMapper.map(savedHunting, ViewHuntingDTO.class);
            }else {
                 throw new CannotStoreHuntingException(hunting.getFish().getAverageWeight());
            }
        }else {
            Hunting huntingModel = modelMapper.map(huntingDTO, Hunting.class);
            huntingModel.setFish(fishRepository.findById(huntingModel.getFish().getName()).get());
            huntingModel.setMember(memberRepository.findById(huntingDTO.getMember().getNum()).get());
            huntingModel.setCompetition(competitionRepository.findById(huntingDTO.getCompetition().getCode()).get());
            Ranking ranking = rankingService.updateScore(huntingModel);
            return modelMapper.map(huntingRepository.save(huntingModel), ViewHuntingDTO.class);
        }
    }

    public boolean canStoreHuntingRecord(Hunting hunting, Double caughtFishWeight){
        Double averageWeight = hunting.getFish().getAverageWeight();
        return averageWeight <= caughtFishWeight;
    }
}
