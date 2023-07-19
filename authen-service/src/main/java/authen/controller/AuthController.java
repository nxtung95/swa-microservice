package authen.controller;

import authen.entity.User;
import authen.object.LoginRequest;
import authen.object.LoginResponse;
import authen.service.AuthService;
import authen.service.JwtService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            if (Strings.isNullOrEmpty(request.getUsername()) || Strings.isNullOrEmpty(request.getPassword())) {
                throw new Exception("UserName or Password is Empty");
            }
            log.info("Login with user: " + request.getUsername());
            User resUser = authService.login(request);
            if (Objects.isNull(resUser)) {
                throw new Exception("UserName or Password is invalid");
            }
            LoginResponse rp = LoginResponse.builder()
                    .username(resUser.getUsername())
                    .role(resUser.getRole())
                    .id(resUser.getId())
                    .token(jwtService.generateToken(resUser))
                    .build();
            return ResponseEntity.ok(rp);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
