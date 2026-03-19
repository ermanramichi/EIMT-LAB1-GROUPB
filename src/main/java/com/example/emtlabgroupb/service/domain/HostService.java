package com.example.emtlabgroupb.service.domain;

import com.example.emtlabgroupb.model.domain.Host;

import java.util.Optional;

public interface HostService {
    Optional<Host> findById(Long id);
}
