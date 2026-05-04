package com.example.emtlabgroupb.model.views;

import com.example.emtlabgroupb.model.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "category_stats")
@Getter
@NoArgsConstructor
public class CategoryStatsEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private Category category;

    private Long totalAccommodations;

    private Long totalRooms;

    private Double avgRooms;
}
