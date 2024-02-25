package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Member.CreateMemberDTO;
import com.example.aftas.Entities.DTOs.Member.ViewMemberDTO;
import com.example.aftas.Entities.DTOs.Request.AuthenticationRequest;
import com.example.aftas.Repositories.MemberRepository;
import com.example.aftas.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Override
    public ViewMemberDTO register(CreateMemberDTO request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return modelMapper.map(request, ViewMemberDTO.class);
    }

    @Override
    public String login(AuthenticationRequest request) {

        return null;
    }
}
