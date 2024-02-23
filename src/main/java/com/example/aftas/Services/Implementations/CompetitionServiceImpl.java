package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.ViewCompetitionDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Enums.Status;
import com.example.aftas.Exceptions.CompetitionAlreadyExistsException;
import com.example.aftas.Repositories.CompetitionRepository;
import com.example.aftas.Services.CompetitionService;
import com.example.aftas.Utils.CompetitionCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service("competitionServiceImpl")
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
        LocalDateTime competitionStartDateTime = LocalDateTime.of(
                competitionDTO.getDate().toLocalDate(),
                competitionDTO.getStartTime().toLocalTime()
        );

        long hoursUntilStart = ChronoUnit.HOURS.between(currentDateTime, competitionStartDateTime);

        return hoursUntilStart > 24;
    }

    @Override
    public CompetitionDTO findCompetitionByCode(String code) {
        return modelMapper.map(competitionRepository.findCompetitionByCode(code), CompetitionDTO.class);
    }

    @Override
    public boolean isCompetitionOngoing(Competition competition) {
            LocalTime startTime = competition.getStartTime().toLocalTime();
            LocalTime endTime = competition.getEndTime().toLocalTime();
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            LocalDate date = competition.getDate().toLocalDate();
            return date.equals(currentDate) &&
                    (currentTime.equals(startTime) || (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)));
    }

    @Override
    public boolean isCompetitionExpired(Competition competition){
        LocalDate competitonDate = competition.getDate().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(competitonDate);
    }

    @Override
    public boolean isCompetitionUpcoming(Competition competition){
        LocalDate competitonDate = competition.getDate().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return currentDate.isBefore(competitonDate);
    }


    @Override
    public boolean isRegistrationOpenForMemberService(Competition competition) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime competitionStartDateTime = LocalDateTime.of(
                competition.getDate().toLocalDate(),
                competition.getStartTime().toLocalTime()
        );

        long hoursUntilStart = ChronoUnit.HOURS.between(currentDateTime, competitionStartDateTime);

        return hoursUntilStart > 24;
    }

    @Override
    public Page<ViewCompetitionDTO> findAllPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("date")));
        return competitionRepository.
                findAll(pageRequest).map(competition -> modelMapper.map(competition, ViewCompetitionDTO.class));
    }
}
