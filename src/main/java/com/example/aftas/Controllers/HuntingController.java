package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Hunting.CreateHuntingDTO;
import com.example.aftas.Entities.Models.Hunting;
import com.example.aftas.Repositories.HuntingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/huntings")
@RequiredArgsConstructor
public class HuntingController {
    private final HuntingRepository huntingRepository;

    @GetMapping("/exists")
    public boolean doesHuntingExists(@RequestBody CreateHuntingDTO huntingDTO){
        String code = huntingDTO.getCompetition().getCode();
        String name = huntingDTO.getFish().getName();
        Integer num = huntingDTO.getMember().getNum();
        Optional<Hunting> optional = huntingRepository.findByCompetitionCode(code);
        return optional.isPresent();
    }
}
