package com.example.emtlabgroupb.model.views;

import com.example.emtlabgroupb.model.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "accommodation_view")
@Getter
@NoArgsConstructor
public class AccommodationViewEntity {

    @Id
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer numRooms;

    private String hostFullName;

    private String countryName;
}
