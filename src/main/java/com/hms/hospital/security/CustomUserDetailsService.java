package com.hms.hospital.security;

import com.hms.hospital.entity.User;
import com.hms.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            User user1 = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user1.getUsername())
                    .password(user1.getPassword())
                    .roles(user1.getRole().toString())
                    .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }
}
