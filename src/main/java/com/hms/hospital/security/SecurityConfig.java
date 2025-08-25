package com.hms.hospital.security;
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {



    @Autowired
    private CustomAuthSuccessHandler successHandler;

    @Autowired
    private final CustomUserDetailsService userService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userService) {
        this.userService = userService;
    }


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/all/**", "/users/**", "/css/**", "/js/**").permitAll();
                    auth.requestMatchers("/admin/**","/appointments/**").hasRole("ADMIN");
                    auth.requestMatchers("/patient/**","/appointments/**").hasRole("PATIENT");
                    auth.requestMatchers("/doctors/**","/appointments/**").hasRole("DOCTOR");
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/login").permitAll();
                    form.successHandler(successHandler);
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/all/")
                            .permitAll();
                })
                .build();
    }

}