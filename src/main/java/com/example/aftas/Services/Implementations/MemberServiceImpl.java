package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Services.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberServiceImpl")
public class MemberServiceImpl implements MemberService {
    @Override
    public List<MemberDTO> findAll() {
        return null;
    }
}
