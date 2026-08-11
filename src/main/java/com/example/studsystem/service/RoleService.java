package com.example.studsystem.service;

import org.springframework.stereotype.Service;

import com.example.studsystem.models.Role;
import com.example.studsystem.repo.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_TEACHER").get();
    }
}
