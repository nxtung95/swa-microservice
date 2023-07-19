package teacher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import teacher.entity.Teacher;
import teacher.service.CommandTeacherService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandTeacherController {
    @Autowired
    private CommandTeacherService commandTeacherService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/teachers/", method = RequestMethod.POST)
    public ResponseEntity<Teacher> add(@RequestBody Teacher teacher) {
        try {
            Teacher resTeacher = commandTeacherService.save(teacher);
            log.info("AddTeacher: " + objectMapper.writeValueAsString(resTeacher));
            if (Objects.isNull(resTeacher)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(resTeacher);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/teachers/", method = RequestMethod.PUT)
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher) {
        try {
            Teacher resTeacher = commandTeacherService.update(teacher);
            log.info("UpdateTeacher: " + objectMapper.writeValueAsString(resTeacher));
            if (Objects.isNull(resTeacher)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(resTeacher);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/teachers/", method = RequestMethod.DELETE)
    public ResponseEntity<Teacher> delete(@RequestBody Teacher teacher) {
        try {
            commandTeacherService.delete(teacher);
            log.info("DeleteUser: " + objectMapper.writeValueAsString(teacher));
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
