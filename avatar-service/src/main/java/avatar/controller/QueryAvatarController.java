package avatar.controller;

import avatar.entity.Avatar;
import avatar.service.QueryAvatarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class QueryAvatarController {
    @Autowired
    private QueryAvatarService queryAvatarService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/avatars/{id}", method = RequestMethod.GET)
    public ResponseEntity<Avatar> view(@PathVariable(value = "id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Avatar avatar = queryAvatarService.findAvatarById(id);
            log.info("ViewAvatar: " + objectMapper.writeValueAsString(avatar));
            if (Objects.isNull(avatar)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.ok(avatar);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
