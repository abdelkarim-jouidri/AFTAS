package com.example.aftas.Entities.Models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RankingKey implements Serializable {
    private Integer num;
    private String code;
}
