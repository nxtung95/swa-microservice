package avatar.controller;

import avatar.entity.Avatar;
import avatar.object.AvatarRequest;
import avatar.object.AvatarResponse;
import avatar.producer.AvatarProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandAvatarController {
    @Autowired
    private AvatarProducer avatarProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/avatars", method = RequestMethod.POST)
    public ResponseEntity<AvatarResponse> add(@RequestBody AvatarRequest avatar) {
        AvatarResponse res = AvatarResponse.builder().build();
        try {
            avatar.setId(UUID.randomUUID().toString());
            log.info("AddAvatar: " + objectMapper.writeValueAsString(avatar));
            Avatar rp = avatarProducer.save(avatar);
            res.setDesc("Success");
            res.setAvatar(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/avatars", method = RequestMethod.PUT)
    public ResponseEntity<AvatarResponse> update(@RequestBody AvatarRequest avatar) {
        AvatarResponse res = AvatarResponse.builder().build();
        try {
            log.info("UpdateAvatar: " + objectMapper.writeValueAsString(avatar));
            Avatar rp = avatarProducer.update(avatar);
            res.setDesc("Success");
            res.setAvatar(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/avatars", method = RequestMethod.DELETE)
    public ResponseEntity<AvatarResponse> delete(@RequestBody AvatarRequest avatar) {
        AvatarResponse res = AvatarResponse.builder().build();
        try {
            log.info("DeleteAvatar: " + objectMapper.writeValueAsString(avatar));
            Avatar rp = avatarProducer.delete(avatar);
            res.setDesc("Success");
            res.setAvatar(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
