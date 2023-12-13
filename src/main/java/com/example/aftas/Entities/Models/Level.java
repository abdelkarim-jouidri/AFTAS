package com.example.aftas.Entities.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
@Builder
public class Level {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;
    private String description;
    private Integer points;

    @OneToMany(mappedBy = "level")
    private Set<Fish> fishes;
}

