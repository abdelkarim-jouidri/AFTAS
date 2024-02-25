package com.example.aftas.Services;

import com.example.aftas.Entities.DTOs.Member.CreateMemberDTO;
import com.example.aftas.Entities.DTOs.Member.ViewMemberDTO;
import com.example.aftas.Entities.DTOs.Request.AuthenticationRequest;

public interface AuthService {
    ViewMemberDTO register(CreateMemberDTO request);
    String login(AuthenticationRequest request);
}
