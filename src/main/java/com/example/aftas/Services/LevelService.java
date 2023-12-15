package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Level.CreateLevelDTO;
import com.example.aftas.Entities.DTOs.Level.LevelDTO;
import org.springframework.stereotype.Component;

@Component
public interface LevelService {
    LevelDTO storeLevel(CreateLevelDTO levelDTO);
}
