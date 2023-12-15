package com.example.aftas.Services.Implementations;

import com.example.aftas.Entities.DTOs.Member.CreateMemberDTO;
import com.example.aftas.Entities.DTOs.Member.MemberDTO;
import com.example.aftas.Entities.Models.Member;
import com.example.aftas.Repositories.MemberRepository;
import com.example.aftas.Services.MemberService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service("memberServiceImpl")
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<MemberDTO> findAll() {
        return memberRepository.
                findAll().
                stream().
                map(member -> modelMapper.map(member, MemberDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MemberDTO createMember(CreateMemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        return modelMapper.map(memberRepository.save(member), MemberDTO.class);
    }
}
