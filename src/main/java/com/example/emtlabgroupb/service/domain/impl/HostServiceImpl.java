package com.example.emtlabgroupb.service.domain.impl;

import com.example.emtlabgroupb.model.domain.Host;
import com.example.emtlabgroupb.repository.HostRepository;
import com.example.emtlabgroupb.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }
}
