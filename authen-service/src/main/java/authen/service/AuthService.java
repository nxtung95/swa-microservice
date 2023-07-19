package authen.service;

import authen.entity.User;
import authen.object.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {
    User login(LoginRequest request) throws JsonProcessingException;
}
