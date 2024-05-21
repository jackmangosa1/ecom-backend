package com.auca.Shopy.dto;

import com.auca.Shopy.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String username;
    private UserRole role;
}
