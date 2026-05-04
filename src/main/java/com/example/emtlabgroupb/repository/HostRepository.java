package com.example.emtlabgroupb.repository;

import com.example.emtlabgroupb.model.domain.Host;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

    @Override
    @EntityGraph(attributePaths = {"country"})
    List<Host> findAll();

    @EntityGraph(attributePaths = {"country"})
    Optional<Host> findWithCountryById(Long id);
}
