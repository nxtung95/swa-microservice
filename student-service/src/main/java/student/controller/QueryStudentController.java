package student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import student.entity.Student;
import student.service.QueryStudentService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class QueryStudentController {
    @Autowired
    private QueryStudentService queryStudentService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> view(@PathVariable(value = "id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Student Student = queryStudentService.findStudentById(id);
            log.info("ViewStudent: " + objectMapper.writeValueAsString(Student));
            if (Objects.isNull(Student)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(Student);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
