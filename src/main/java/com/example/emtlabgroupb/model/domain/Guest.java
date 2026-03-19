package com.example.emtlabgroupb.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guests")
@Data
@NoArgsConstructor
public class Guest extends BaseEntity {

    private String name;
    private String email;
    private String passport;

    @ManyToMany(mappedBy = "guests")
    private Set<Host> hosts = new HashSet<>();

    public Guest(String name, String email, String passport) {
        this.name = name;
        this.email = email;
        this.passport = passport;
    }
}