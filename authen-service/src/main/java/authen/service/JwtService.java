package authen.service;

import authen.entity.User;

public interface JwtService {
    String generateToken(User user);
}
