package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Hunting.CreateHuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.HuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.ViewHuntingDTO;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Exceptions.CannotStoreHuntingException;
import com.example.aftas.Repositories.HuntingRepository;
import com.example.aftas.Services.HuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final ModelMapper modelMapper;
    @Override
    public ViewHuntingDTO saveHunting(CreateHuntingDTO huntingDTO) {
        String code = huntingDTO.getCompetition().getCode();
        Integer num = huntingDTO.getMember().getNum();
        String name = huntingDTO.getFish().getName();
        Optional<Hunting> optionalExistingHunting = huntingRepository.findByCompetitionCodeAndMemberNumAndFishName(code, num, name);
        if (optionalExistingHunting.isPresent()){
            Hunting hunting = optionalExistingHunting.get();
            Double caughtFishWeight = huntingDTO.getCaughtFishWeight();
            int numberOfFishes = hunting.getNumberOfFishes();
            if(canStoreHuntingRecord(hunting, caughtFishWeight)){
                hunting.setNumberOfFishes(numberOfFishes + huntingDTO.getNumberOfFishes());
                Hunting savedHunting = huntingRepository.save(hunting);
                // problem with model mapper not mapping and throwing an exception
                return modelMapper.map(savedHunting, ViewHuntingDTO.class);
            }else {
                 throw new CannotStoreHuntingException(hunting.getFish().getAverageWeight());
            }
        }else {
            Hunting huntingModel = modelMapper.map(huntingDTO, Hunting.class);
            return modelMapper.map(huntingRepository.save(huntingModel), ViewHuntingDTO.class);
        }
    }

    public boolean canStoreHuntingRecord(Hunting hunting, Double caughtFishWeight){
        Double averageWeight = hunting.getFish().getAverageWeight();
        if(averageWeight <= caughtFishWeight) return true;
        return false;
    }
}
