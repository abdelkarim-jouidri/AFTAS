package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Hunting.CreateHuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.HuntingDTO;
import org.springframework.stereotype.Component;

@Component
public interface HuntingService {
    HuntingDTO saveHunting(CreateHuntingDTO huntingDTO);
}
