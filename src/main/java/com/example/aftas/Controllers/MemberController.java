package com.example.aftas.Controllers;

import com.example.aftas.Entities.DTOs.Member.CreateMemberDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Entities.DTOs.Ranking.RankingDTO;
import com.example.aftas.Entities.DTOs.Response.CustomResponse;
import com.example.aftas.Exceptions.CompetitionRegistrationException;
import com.example.aftas.Services.MemberService;
import com.example.aftas.Services.RankingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("api/members/")
@AllArgsConstructor
public class MemberController {
    @Qualifier("memberServiceImpl")
    private final MemberService memberService;

    private final RankingService rankingService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<MemberDTO>, String>> getAllMembers(){
        try{
            List<MemberDTO> allMembers = memberService.findAll();
            CustomResponse<List<MemberDTO>, String> response = new CustomResponse<>("All members", allMembers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<MemberDTO, String>> AddMember(@Valid @RequestBody CreateMemberDTO memberDTO){
        try {
            MemberDTO storedMemberDTO = memberService.createMember(memberDTO);
            CustomResponse<MemberDTO, String> response = new CustomResponse<>("Member successfully added ", storedMemberDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse<RankingDTO, String>> registerMember(@RequestParam Integer memberNum, @RequestParam String competitionCode){
        try {
            RankingDTO registeredRankingDTO = memberService.registerMemberForCompetition(memberNum, competitionCode);
            CustomResponse<RankingDTO, String> response = new CustomResponse<>("Member successfully registered for the competition", registeredRankingDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
