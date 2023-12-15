package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MemberService {
    List<MemberDTO> findAll();
}
