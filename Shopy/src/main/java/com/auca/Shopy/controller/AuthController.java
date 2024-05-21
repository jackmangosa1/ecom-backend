package com.auca.Shopy.controller;

import com.auca.Shopy.dto.AuthenticationRequestDTO;
import com.auca.Shopy.dto.SignupRequestDTO;
import com.auca.Shopy.dto.UserDTO;
import com.auca.Shopy.enums.UserRole;
import com.auca.Shopy.model.User;
import com.auca.Shopy.repository.UserRepository;
import com.auca.Shopy.service.auth.AuthService;
import com.auca.Shopy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private  final JwtUtil jwtUtil;
    private  final AuthService authService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO authRequest) {
        Optional<User> optionalUser = userRepository.findFirstByUsername(authRequest.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (bCryptPasswordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                final String jwt = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRole().toString());
                return ResponseEntity.ok(jwt);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequestDTO signupRequest){
        if(authService.hasUserWithEmail(signupRequest.getEmail())){
            return  new ResponseEntity<>("User email already exist", HttpStatus.NOT_ACCEPTABLE);
        }
        if(authService.hasUserWithUsername(signupRequest.getUsername())){
            return  new ResponseEntity<>("Username already exist", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO userDto = authService.createUser(signupRequest);
        userDto.setEmail(signupRequest.getEmail());
        userDto.setName(signupRequest.getName());
        userDto.setUsername(signupRequest.getUsername());
        userDto.setRole(UserRole.USER);
        return  new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
