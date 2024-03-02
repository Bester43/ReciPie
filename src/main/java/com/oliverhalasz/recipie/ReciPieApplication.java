package com.oliverhalasz.recipie;

import com.oliverhalasz.recipie.models.Role;
import com.oliverhalasz.recipie.models.User;
import com.oliverhalasz.recipie.repositories.RoleRepository;
import com.oliverhalasz.recipie.repositories.UserRepository;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Theme("ReciPie")
@SpringBootApplication
public class ReciPieApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(ReciPieApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args ->{
            if (roleRepository.findByAuthority("USER").isPresent()) return;

            Role userRole = roleRepository.save(new Role("USER"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            User admin = new User(1L, "john", passwordEncoder.encode("password"), userRoles);
            User user = new User(2L, "jane", passwordEncoder.encode("password"), userRoles);

            userRepository.save(admin);
            userRepository.save(user);
        };
    }
}
