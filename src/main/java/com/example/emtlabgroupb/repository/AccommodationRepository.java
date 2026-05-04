package com.example.emtlabgroupb.repository;

import com.example.emtlabgroupb.model.domain.Accommodation;
import com.example.emtlabgroupb.model.domain.Category;
import com.example.emtlabgroupb.model.projection.AccommodationExtendedProjection;
import com.example.emtlabgroupb.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    // Task 2 – short projection
    Page<AccommodationShortProjection> findAllProjectedBy(Pageable pageable);

    // Task 2 – extended projection
    @Query("""
            SELECT a.id AS id, a.name AS name, a.category AS category, a.numRooms AS numRooms,
                   h.name AS hostFirstName, h.surname AS hostLastName, c.name AS hostCountry
            FROM Accommodation a
            JOIN a.host h
            JOIN h.country c
            """)
    Page<AccommodationExtendedProjection> findAllExtended(Pageable pageable);

    // Task 3 – EntityGraph: load host + country in one query
    @EntityGraph(attributePaths = {"host", "host.country"})
    Optional<Accommodation> findWithHostById(Long id);

    // Task 1 – filtered listing with pagination
    @Query(value = """
            SELECT a FROM Accommodation a
            JOIN a.host h
            JOIN h.country c
            WHERE (:category IS NULL OR a.category = :category)
              AND (:hostId IS NULL OR h.id = :hostId)
              AND (:countryName IS NULL OR LOWER(c.name) = LOWER(:countryName))
              AND (:numRooms IS NULL OR a.numRooms = :numRooms)
              AND (:hasAvailableRooms IS NULL
                    OR (:hasAvailableRooms = TRUE AND a.numRooms > 0)
                    OR (:hasAvailableRooms = FALSE AND a.numRooms = 0))
            """,
            countQuery = """
            SELECT COUNT(a) FROM Accommodation a
            JOIN a.host h
            JOIN h.country c
            WHERE (:category IS NULL OR a.category = :category)
              AND (:hostId IS NULL OR h.id = :hostId)
              AND (:countryName IS NULL OR LOWER(c.name) = LOWER(:countryName))
              AND (:numRooms IS NULL OR a.numRooms = :numRooms)
              AND (:hasAvailableRooms IS NULL
                    OR (:hasAvailableRooms = TRUE AND a.numRooms > 0)
                    OR (:hasAvailableRooms = FALSE AND a.numRooms = 0))
            """)
    Page<Accommodation> findFiltered(
            @Param("category") Category category,
            @Param("hostId") Long hostId,
            @Param("countryName") String countryName,
            @Param("numRooms") Integer numRooms,
            @Param("hasAvailableRooms") Boolean hasAvailableRooms,
            Pageable pageable
    );
}
