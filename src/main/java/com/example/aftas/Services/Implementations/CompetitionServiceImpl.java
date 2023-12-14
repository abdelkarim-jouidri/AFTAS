package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Exceptions.CompetitionAlreadyExistsException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Services.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<CompetitionDTO> findAll() {
        return null;
    }

    @Override
    public CompetitionDTO StoreCompetition(CreateCompetitionDTO competitionDTO) {
        Date date = competitionDTO.getDate();
        if(competitionRepository.existsByDate(date))
            throw new CompetitionAlreadyExistsException(date);
        Competition competitionEntity = modelMapper.map(competitionDTO, Competition.class);
        String locationCode = competitionEntity.getLocation().substring(0, Math.min(competitionEntity.getLocation().length(), 3));
        String dateCode = competitionEntity.getDate().toLocalDate().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        String code = locationCode.toLowerCase()+"-"+dateCode;
        competitionEntity.setCode(code);
        Competition savedCompetition = competitionRepository.save(competitionEntity);
        if(savedCompetition==null){
            throw new RuntimeException("Something went wrong");
        }
        return modelMapper.map(savedCompetition, CompetitionDTO.class);

    }
}
