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
import user.object.UserRequest;
import user.object.UserResponse;
import user.producer.UserProducer;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandUserController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserProducer userProducer;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> add(@RequestBody UserRequest user) {
        UserResponse res = UserResponse.builder().build();
        try {
            user.setId(UUID.randomUUID().toString());
            log.info("AddUser: " + objectMapper.writeValueAsString(user));
            User userEntity = userProducer.save(user);
            res.setUser(userEntity);
            res.setDesc("Success");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<UserResponse> update(@RequestBody UserRequest user) {
        UserResponse res = UserResponse.builder().build();
        try {
            log.info("UpdateUser: " + objectMapper.writeValueAsString(user));
            User userEntity = userProducer.update(user);
            res.setUser(userEntity);
            res.setDesc("Success");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<UserResponse> delete(@RequestBody UserRequest user) {
        UserResponse res = UserResponse.builder().build();
        try {
            log.info("DeleteUser: " + objectMapper.writeValueAsString(user));
            User userEntity = userProducer.delete(user);
            res.setUser(userEntity);
            res.setDesc("Success");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
