package com.example.emtlabgroupb.repository;

import com.example.emtlabgroupb.model.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findAllByHosts_Id(Long hostId);
}