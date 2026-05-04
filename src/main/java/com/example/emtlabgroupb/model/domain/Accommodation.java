package com.example.emtlabgroupb.model.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accommodations")
@Getter
@Setter
@NoArgsConstructor
public class Accommodation extends BaseAuditableEntity{

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condition condition;

    @Column(nullable = false)
    private Integer numRooms;

    @Column(nullable = false)
    private Boolean rented = true;

    public Accommodation(String name, Category category, Host host, Condition condition, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.condition = condition;
        this.numRooms = numRooms;
    }
}
