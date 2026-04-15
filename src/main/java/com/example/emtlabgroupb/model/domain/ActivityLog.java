package com.example.emtlabgroupb.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_log")
@Getter
@Setter
@NoArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accommodationName;

    @Column(nullable = false)
    private LocalDateTime eventTimestamp;

    @Column(nullable = false)
    private String eventType;

    private Long accommodationId;

    private Long hostId;

    public ActivityLog(String accommodationName, LocalDateTime eventTimestamp,
                       String eventType, Long accommodationId, Long hostId) {
        this.accommodationName = accommodationName;
        this.eventTimestamp = eventTimestamp;
        this.eventType = eventType;
        this.accommodationId = accommodationId;
        this.hostId = hostId;
    }
}