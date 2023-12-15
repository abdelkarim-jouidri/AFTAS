package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Enums.Status;
import com.example.aftas.Exceptions.CompetitionAlreadyExistsException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Services.CompetitionService;
import com.example.aftas.Utils.CompetitionCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<CompetitionDTO> findAll() {
        return competitionRepository.
                findAll().
                stream().
                map(competition -> modelMapper.map(competition, CompetitionDTO.class)).
                collect(Collectors.toList());
    }

    @Override
    public CompetitionDTO StoreCompetition(CreateCompetitionDTO competitionDTO) {
        Date date = competitionDTO.getDate();
        if(competitionRepository.existsByDate(date))
            throw new CompetitionAlreadyExistsException(date);
        Competition competitionEntity = modelMapper.map(competitionDTO, Competition.class);
        String code = CompetitionCodeGenerator.generateCode(competitionEntity.getLocation(), competitionEntity.getDate());
        competitionEntity.setCode(code);
        Competition savedCompetition = competitionRepository.save(competitionEntity);
        if(savedCompetition==null){
            throw new RuntimeException("Something went wrong");
        }
        return modelMapper.map(savedCompetition, CompetitionDTO.class);

    }

    @Override
    public List<CompetitionDTO> filterByStatus(Status status) {
        List<CompetitionDTO> allCompetitionsDTOs = findAll();
        if(status == Status.CLOSED) return allCompetitionsDTOs.
                stream().
                filter(competition->!isRegistrationOpen(competition)).collect(Collectors.toList());
        else return allCompetitionsDTOs.
                stream().
                filter(competition->isRegistrationOpen(competition)).collect(Collectors.toList());
    }

    @Override
    public boolean isRegistrationOpen(CompetitionDTO competitionDTO) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Combine the competition date and start time
        LocalDateTime competitionStartDateTime = LocalDateTime.of(
                competitionDTO.getDate().toLocalDate(),
                competitionDTO.getStartTime().toLocalTime()
        );

        // Calculate the difference in hours between the current time and competition start time
        long hoursUntilStart = ChronoUnit.HOURS.between(currentDateTime, competitionStartDateTime);

        // Return true if registration is allowed (24 hours before the start)
        return hoursUntilStart > 24;
    }
}
