package com.example.studsystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studsystem.models.Role;
import com.example.studsystem.repo.RoleRepository;

@Service
public class RoleService {
	@Autowired
    private RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_TEACHER").get();
    }
}