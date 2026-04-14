package com.example.emtlabgroupb.repository;

import com.example.emtlabgroupb.model.views.AccommodationViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationViewRepository extends JpaRepository<AccommodationViewEntity, Long> {
    Page<AccommodationViewEntity> findAll(Pageable pageable);
}
