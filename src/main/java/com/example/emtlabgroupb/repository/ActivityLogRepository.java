package com.example.emtlabgroupb.repository;

import com.example.emtlabgroupb.model.domain.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    Page<ActivityLog> findAll(Pageable pageable);

    @Query("""
            SELECT a.accommodationId, a.accommodationName, COUNT(a) AS bookingCount
            FROM ActivityLog a
            WHERE a.eventType = 'ACCOMMODATION_RENTED'
            GROUP BY a.accommodationId, a.accommodationName
            ORDER BY bookingCount DESC
            """)
    Page<Object[]> findMostPopularAccommodations(Pageable pageable);

    @Query("""
            SELECT a.hostId, COUNT(a) AS bookingCount
            FROM ActivityLog a
            WHERE a.eventType = 'ACCOMMODATION_RENTED'
            GROUP BY a.hostId
            ORDER BY bookingCount DESC
            """)
    Page<Object[]> findMostPopularHosts(Pageable pageable);
}