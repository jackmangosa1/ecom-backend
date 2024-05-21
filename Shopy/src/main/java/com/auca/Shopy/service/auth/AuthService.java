package com.auca.Shopy.service.auth;

import com.auca.Shopy.dto.SignupRequestDTO;
import com.auca.Shopy.dto.UserDTO;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;


public interface AuthService {
    UserDTO createUser(SignupRequestDTO signupRequest);
    Boolean hasUserWithEmail(String email);

    Boolean hasUserWithUsername(String username);
    ResponseEntity<?> authenticate(String username, String password) throws JSONException;
}
