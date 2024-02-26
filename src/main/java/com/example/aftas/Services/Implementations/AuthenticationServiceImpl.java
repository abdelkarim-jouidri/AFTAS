package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Request.AuthenticationRequest;
import com.example.aftas.Entities.DTOs.Request.RegisterRequest;
import com.example.aftas.Entities.DTOs.Response.AuthenticationResponse;
import com.example.aftas.Entities.Models.Competition;
import com.example.aftas.Entities.Models.Member;
import com.example.aftas.Repositories.MemberRepository;
import com.example.aftas.Services.AuthenticationService;
import com.example.aftas.Services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Member builtMember = Member.
                builder().
                email(request.getEmail()).
                firstname(request.getFirstName()).
                lastname(request.getLastName()).
                password(passwordEncoder.encode(request.getPassword())).
                role(request.getRole()).
                build();
        memberRepository.save(builtMember);
        var jwt = jwtService.generateToken(builtMember);
        return AuthenticationResponse.
                builder().
                token(jwt).
                build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Member member = memberRepository.
                findMemberByEmail(request.getEmail()).
                orElseThrow(() -> new UsernameNotFoundException("Wrong Credentials"));
        if(!member.isAccountActivated()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Account Not Activated");
        }
        var jwt = jwtService.generateToken(member);
        return AuthenticationResponse.
                builder().
                token(jwt).
                build();
    }
}
