package authen.service.impl;

import authen.entity.User;
import authen.object.LoginRequest;
import authen.repository.AuthRepository;
import authen.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(LoginRequest request) throws JsonProcessingException {
        Optional<User> optUser = authRepository.findFirstByUsername(request.getUsername());
        if (!optUser.isPresent()) {
            return null;
        }
        User user = optUser.get();
        log.info("User@login: " + objectMapper.writeValueAsString(user));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return null;
        }
        return user;
    }
}
