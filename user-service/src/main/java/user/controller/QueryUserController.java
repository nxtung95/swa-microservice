package user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.entity.User;
import user.service.QueryUserService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class QueryUserController {
    @Autowired
    private QueryUserService queryUserService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> view(@PathVariable(value = "id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            User user = queryUserService.findUserById(id);
            log.info("ViewUser: " + objectMapper.writeValueAsString(user));
            if (Objects.isNull(user)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
