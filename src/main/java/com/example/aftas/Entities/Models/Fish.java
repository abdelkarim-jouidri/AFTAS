package com.example.aftas.Entities.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Fish {
    @Id
    private String name;
    private Double averageWeight;

    @OneToMany(mappedBy = "fish")
    private Set<Hunting> huntings = new HashSet<>();

    @ManyToOne @JoinColumn(name = "level_id")
    private Level level;

    public void addHunting(Hunting hunting) {
        this.huntings.add(hunting);
        hunting.setFish(this);
    }
}
