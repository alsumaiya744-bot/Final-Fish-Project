package com.alsultanseafood.fishsupplychainmanagement.config;



import com.alsultanseafood.fishsupplychainmanagement.user.entity.User;
import com.alsultanseafood.fishsupplychainmanagement.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository
                    .findByEmail("malik123@gmail.com")
                    .isEmpty()) {

                User admin = new User();

                admin.setFullName("Malik");
                admin.setEmail("malik123@gmail.com");
                admin.setPhoneNumber("9944974027");

                admin.setAddress(
        "Ramanathapuram");

                admin.setPassword(
                        passwordEncoder.encode("admin123"));

                admin.setRole("ADMIN");
                admin.setCustomerType("WHOLESALE");
                admin.setActive(true);

                userRepository.save(admin);

                System.out.println(
                        "Admin Created Successfully");
            }
        };
    }
}