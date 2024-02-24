package com.example.aftas.Repositories;

import com.example.aftas.Entities.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findMemberByEmail(String email);
}
