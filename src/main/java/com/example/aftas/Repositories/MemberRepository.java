package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Level;
import com.example.aftas.Entities.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
