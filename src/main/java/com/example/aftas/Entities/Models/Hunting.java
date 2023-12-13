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

    @ManyToOne @JoinColumn(name = "fish_id")
    private Fish fish;

    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne @JoinColumn(name = "competition_id")
    private Competition competition;
}
