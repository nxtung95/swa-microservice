package avatar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import element.entity.Element;
import element.service.QueryElementService;
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
    private QueryElementService queryElementService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/elements/{id}", method = RequestMethod.GET)
    public ResponseEntity<Element> view(@PathVariable(value = "id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Element element = queryElementService.findElementById(id);
            log.info("ViewElement: " + objectMapper.writeValueAsString(element));
            if (Objects.isNull(element)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.ok(element);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
