package com.example.aftas.Controllers;

import com.example.aftas.Services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/members/")
@AllArgsConstructor
public class MemberController {
    @Qualifier("memberServiceImpl")
    private final MemberService memberService;
}
