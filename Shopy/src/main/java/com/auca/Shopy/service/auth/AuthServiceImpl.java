package com.auca.Shopy.service.auth;

import com.auca.Shopy.dto.SignupRequestDTO;
import com.auca.Shopy.dto.UserDTO;
import com.auca.Shopy.enums.OrderStatus;
import com.auca.Shopy.enums.UserRole;
import com.auca.Shopy.model.Order;
import com.auca.Shopy.model.User;
import com.auca.Shopy.repository.OrderRepository;
import com.auca.Shopy.repository.UserRepository;
import com.auca.Shopy.utils.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;
    private final JwtUtil jwtUtil;

    public final String TOKEN_PREFIX = "Bearer ";
    public final String HEADER_STRING = "Authorization";

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, // Inject PasswordEncoder interface
                           OrderRepository orderRepository,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // Spring will inject the bean here
        this.orderRepository = orderRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> authenticate(String username, String password) throws JSONException {
        Optional<User> optionalUser = userRepository.findFirstByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) { // Use passwordEncoder
                final String jwt = jwtUtil.generateToken(user.getUsername(), user.getId(), user.getRole().toString());
                return ResponseEntity.ok()
                        .header(HEADER_STRING, TOKEN_PREFIX + jwt)
                        .body(new JSONObject()
                                .put("userId", user.getId())
                                .put("username", user.getUsername())
                                .put("role", user.getRole().toString())
                                .toString());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }
    }

    public UserDTO createUser(SignupRequestDTO signupRequest) {
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword())); // Use passwordEncoder
        user.setRole(UserRole.USER);
        User createdUser = userRepository.save(user);

        Order order = new Order();
        order.setAmount(0L);
        order.setUser(createdUser);
        order.setOrderStatus(OrderStatus.Cooking);
        orderRepository.save(order);

        UserDTO userDto = new UserDTO();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    public Boolean hasUserWithUsername(String username) {
        return userRepository.findFirstByUsername(username).isPresent();
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (adminAccount == null) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setUsername("admin");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(passwordEncoder.encode("test123")); // Use passwordEncoder
            userRepository.save(user);
        }
    }
}
