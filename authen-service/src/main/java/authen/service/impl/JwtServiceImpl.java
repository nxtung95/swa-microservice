package authen.service.impl;

import authen.entity.User;
import authen.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret:swa-project}")
    private String secret;

    @Override
    public String generateToken(User user) {
        String jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, secret).compact();
        return jwtToken;
    }
}
