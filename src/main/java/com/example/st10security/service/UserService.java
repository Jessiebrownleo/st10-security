package com.example.st10security.service;

import com.example.st10security.dto.UserRequest;
import com.example.st10security.model.Role;
import com.example.st10security.model.User;
import com.example.st10security.repository.RoleRepository;
import com.example.st10security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //Get all User
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Get User By ID
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    // Create User
    public User save(UserRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
        user.setRoles(Collections.singleton(role));
         return userRepository.save(user);

    }

    //Delete User
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }


    //Update User
    public User updateUser(Long id,UserRequest request){
       User user= userRepository.findById(id).orElseThrow();
       user.setUsername(request.getUsername());
       user.setPassword(request.getPassword());

       return userRepository.save(user);
    }
}
