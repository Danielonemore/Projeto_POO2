package br.com.clinicavt.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;


    public static final List<String> WHITELIST = Arrays.asList(
            "/actuator/*",
            "/configuration/security",
            "/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-resources/configuration/security",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/webjars/swagger-ui/**",
            "/error"
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // n guarda estado
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(WHITELIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        .requestMatchers(HttpMethod.POST, "/email").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/auth/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/auth/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/clinicavt/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/clinicavt/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/clinicavt/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/clinicavt/**").hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/clinicavt/pet/id").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/clinicavt/consulta").hasRole("CLIENTE")

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // vai pegar a instancia de authenticationmanager
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
