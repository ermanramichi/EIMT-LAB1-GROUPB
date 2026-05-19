package com.example.emtlabgroupb.config;

import com.example.emtlabgroupb.model.domain.Role;
import com.example.emtlabgroupb.model.domain.User;
import com.example.emtlabgroupb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds a couple of default users on startup so Lab 4 can be evaluated immediately
 * without manually registering users.
 *
 * <ul>
 *   <li>{@code admin / password123} — ROLE_ADMIN (full CRUD)</li>
 *   <li>{@code user  / password123} — ROLE_USER (read-only)</li>
 * </ul>
 *
 * Both users are skipped if they already exist.
 */
@Component
public class DefaultUsersInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DefaultUsersInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUsersInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        ensureUser("admin", "password123", "Default Admin", Role.ROLE_ADMIN);
        ensureUser("user", "password123", "Default User", Role.ROLE_USER);
    }

    private void ensureUser(String username, String rawPassword, String name, Role role) {
        if (userRepository.existsByUsername(username)) {
            return;
        }
        User user = new User(username, passwordEncoder.encode(rawPassword), name, role);
        userRepository.save(user);
        log.info("Seeded default user: {} ({})", username, role.name());
    }
}
