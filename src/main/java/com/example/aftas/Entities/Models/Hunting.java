package com.example.aftas.Entities.Models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@Entity

public class Hunting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numberOfFishes;

    @ManyToOne @JoinColumn(name = "fish_name")
    private Fish fish;

    @ManyToOne @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne @JoinColumn(name = "competition_code")
    private Competition competition;
}
