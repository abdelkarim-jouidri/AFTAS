package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Hunting.CreateHuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.HuntingDTO;
import com.example.aftas.Entities.DTOs.Hunting.ViewHuntingDTO;
import org.springframework.stereotype.Component;

@Component
public interface HuntingService {
    ViewHuntingDTO saveHunting(CreateHuntingDTO huntingDTO);
}
