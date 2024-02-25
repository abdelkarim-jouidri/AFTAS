package com.example.aftas.Entities.Models;

import com.example.aftas.Enums.IdentityDocumentType;
import com.example.aftas.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class Member implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    private String firstname;
    private String lastname;
    private String email;
    private Date joiningDate;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;
    private String password;
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    private Set<Hunting> huntings;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>(List.of(this.role));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
