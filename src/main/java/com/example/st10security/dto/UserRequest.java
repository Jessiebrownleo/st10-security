package com.example.st10security.dto;

import com.example.st10security.model.Role;
import lombok.Data;

@Data
public class UserRequest {
   String username;
   String password;
}
