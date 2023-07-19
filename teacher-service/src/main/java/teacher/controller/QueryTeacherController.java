package teacher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teacher.entity.Teacher;
import teacher.service.QueryTeacherService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class QueryTeacherController {
    @Autowired
    private QueryTeacherService queryTeacherService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> view(@PathVariable(value = "id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Teacher teacher = queryTeacherService.findTeacherById(id);
            log.info("ViewTeacher: " + objectMapper.writeValueAsString(teacher));
            if (Objects.isNull(teacher)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(teacher);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
