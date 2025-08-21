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
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        // Log the role for debugging
//        System.out.println("Assigned role: ROLE_" + user.getRole());
//
//        // Return Spring Security's UserDetails object
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
//        );
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
