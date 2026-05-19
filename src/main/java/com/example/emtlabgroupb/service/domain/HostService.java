package com.example.emtlabgroupb.service.domain;

import com.example.emtlabgroupb.model.domain.Host;
import com.example.emtlabgroupb.model.dto.CreateHostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);
    List<Host> findAll();
    Host save(CreateHostDto dto);
    Optional<Host> update(Long id, CreateHostDto dto);
    Optional<Host> deleteById(Long id);
}
