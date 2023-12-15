package com.example.aftas.Entities.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Fish {
    @Id
    private String name;
    private Double averageWeight;

    @OneToMany(mappedBy = "fish")
    private Set<Hunting> huntings;

    @ManyToOne @JoinColumn(name = "level_id")
    private Level level;
}
