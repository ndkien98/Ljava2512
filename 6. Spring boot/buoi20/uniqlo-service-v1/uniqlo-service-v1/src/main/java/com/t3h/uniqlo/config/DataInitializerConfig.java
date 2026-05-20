package com.t3h.uniqlo.config;

import com.t3h.uniqlo.entity.Role;
import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.repository.RoleRepository;
import com.t3h.uniqlo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializerConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransactionTemplate transactionTemplate;

    @Bean
    public CommandLineRunner initDefaultUsers() {
        return args -> {
            transactionTemplate.execute(status -> {
                log.info("Checking default users and roles...");

            // Khoi tao Roles neu chua co
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_ADMIN");
                return roleRepository.save(role);
            });

            Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
                Role role = new Role();
                role.setName("ROLE_USER");
                return roleRepository.save(role);
            });

            // Khoi tao Admin User
            if (userRepository.findByEmail("admin@uniqlo.com").isEmpty()) {
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);

                User admin = User.builder()
                        .email("admin@uniqlo.com")
                        .passwordHash(passwordEncoder.encode("admin123"))
                        .fullName("System Administrator")
                        .roles(adminRoles)
                        .build();
                userRepository.save(admin);
                log.info("Default Admin user created: admin@uniqlo.com / admin123");
            }

            // Khoi tao Normal User
            if (userRepository.findByEmail("user@uniqlo.com").isEmpty()) {
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(userRole);

                User normalUser = User.builder()
                        .email("user@uniqlo.com")
                        .passwordHash(passwordEncoder.encode("user123"))
                        .fullName("Normal User")
                        .roles(userRoles)
                        .build();
                userRepository.save(normalUser);
                log.info("Default Normal user created: user@uniqlo.com / user123");
            }

            log.info("Default users initialization completed.");
            return null;
            });
        };
    }
}
