package user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import user.entity.User;
import user.service.CommandUserService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandUserController {
    @Autowired
    private CommandUserService commandUserService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/users/", method = RequestMethod.POST)
    public ResponseEntity<User> add(@RequestBody User user) {
        try {
            User resUser = commandUserService.save(user);
            log.info("AddUser: " + objectMapper.writeValueAsString(resUser));
            if (Objects.isNull(resUser)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(resUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/users/", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
            User resUser = commandUserService.update(user);
            log.info("UpdateUser: " + objectMapper.writeValueAsString(resUser));
            if (Objects.isNull(resUser)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(resUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@RequestBody User user) {
        try {
            commandUserService.delete(user);
            log.info("DeleteUser: " + objectMapper.writeValueAsString(user));
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
