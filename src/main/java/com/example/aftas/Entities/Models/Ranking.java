package com.example.aftas.Entities.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
    @EmbeddedId
    private RankingKey id;

    @Column(name = "ranking_rank")
    private Integer rank;
    private Integer score;

    @JoinColumn(name = "num", updatable = false, insertable = false)
    @ManyToOne
    private Member member;

    @JoinColumn(name = "code", updatable = false, insertable = false)
    @ManyToOne
    private Competition competition;
}
