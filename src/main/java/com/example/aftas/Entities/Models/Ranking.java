package com.example.aftas.Entities.Models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ranking {
    @EmbeddedId
    private RankingKey id;

    private Integer rank;
    private Integer score;

    @JoinColumn(name = "num", updatable = false, insertable = false)
    @ManyToOne
    private Member member;

    @JoinColumn(name = "code", updatable = false, insertable = false)
    @ManyToOne
    private Competition competition;
}
