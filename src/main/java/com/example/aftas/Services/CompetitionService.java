package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Competition.CompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.CreateCompetitionDTO;
import com.example.aftas.Entities.DTOs.Competition.ViewCompetitionDTO;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface CompetitionService {
    List<CompetitionDTO> findAll();
    CompetitionDTO StoreCompetition(CreateCompetitionDTO competitionDTO);
    List<CompetitionDTO> filterByStatus(Status status);
    boolean isRegistrationOpen(CompetitionDTO competitionDTO);
    CompetitionDTO findCompetitionByCode(String code);
    boolean isCompetitionOngoing(Competition competition);
    boolean isCompetitionUpcoming(Competition competition);
    boolean isCompetitionExpired(Competition competition);
    boolean isRegistrationOpenForMemberService(Competition competition);

    List<ViewCompetitionDTO> findAllPaginated(int page, int size);
}
