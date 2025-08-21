package com.example.st10security.init;

import com.example.st10security.model.Role;
import com.example.st10security.model.User;
import com.example.st10security.repository.RoleRepository;
import com.example.st10security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUse(RoleRepository roleRepo, UserRepository userRepo,
                              PasswordEncoder encoder){
        return args -> {
            if(roleRepo.count()==0){
                //Admin role
                Role admin = new Role();
                admin.setName("ADMIN");
                roleRepo.save(admin);

                //User role
                Role user = new Role();
                user.setName("USER");
                roleRepo.save(user);
            }
            if(userRepo.findByUsername("admin").isEmpty()){
                Role admin = roleRepo.findByName("ADMIN").orElseThrow();
                User user = new User();
                user.setUsername("admin");
                user.setPassword(encoder.encode("admin123"));
                user.setRoles(Set.of(admin));
                userRepo.save(user);
            }



            if(userRepo.findByUsername("user").isEmpty()){
                Role userRole = roleRepo.findByName("USER").orElseThrow();
                User user = new User();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRoles(Set.of(userRole));
                userRepo.save(user);
            }
        };
    }
}
