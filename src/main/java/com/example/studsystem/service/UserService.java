package com.example.studsystem.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studsystem.dto.RegistrationUserDTO;
import com.example.studsystem.models.User;
import com.example.studsystem.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleService roleService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public Optional<User> findUserById(Long user_id) {
        return userRepository.findById(user_id);
    }

    public List<User> findUserByRole(String rolename){
        return userRepository.findUsersByRoleName(rolename);
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
	
	public User findUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createNewUser(RegistrationUserDTO registrationUserDto) {
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }
}