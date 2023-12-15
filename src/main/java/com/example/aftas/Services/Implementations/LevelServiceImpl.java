package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Level.CreateLevelDTO;
import com.example.aftas.Entities.DTOs.Level.LevelDTO;
import com.example.aftas.Entities.Models.Level;
import com.example.aftas.Exceptions.CannotStoreLevelException;
import com.example.aftas.Repositories.LevelRepository;
import com.example.aftas.Services.LevelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final ModelMapper modelMapper;
    private final LevelRepository levelRepository;
    @Override
    public LevelDTO storeLevel(CreateLevelDTO levelDTO) {
        int heighestLevelPoints = findHeighestLevelPoints();
        if(heighestLevelPoints<levelDTO.getPoints()){
            Level level = modelMapper.map(levelDTO, Level.class);
            Level savedLevel = levelRepository.save(level);
            return modelMapper.map(savedLevel, LevelDTO.class);
        }else {
            throw new CannotStoreLevelException(heighestLevelPoints);
        }
    }

    public int findHeighestLevelPoints(){
        Optional<Level> highestLevel = levelRepository.findAll().stream().max(Comparator.comparingInt(Level::getPoints));
        if(highestLevel.isPresent()) return highestLevel.get().getPoints();
        else return 0;
    }
}
