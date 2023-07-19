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
import teacher.object.TeacherRequest;
import teacher.object.TeacherResponse;
import teacher.producer.TeacherProducer;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandTeacherController {
    @Autowired
    private TeacherProducer teacherProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public ResponseEntity<TeacherResponse> add(@RequestBody TeacherRequest teacher) {
        TeacherResponse res = TeacherResponse.builder().build();
        try {
            teacher.setId(UUID.randomUUID().toString());
            log.info("AddTeacher: " + objectMapper.writeValueAsString(teacher));
            Teacher teacherEntity = teacherProducer.save(teacher);
            res.setDesc("Success");
            res.setTeacher(teacherEntity);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.PUT)
    public ResponseEntity<TeacherResponse> update(@RequestBody TeacherRequest teacher) {
        TeacherResponse res = TeacherResponse.builder().build();
        try {
            log.info("UpdateTeacher: " + objectMapper.writeValueAsString(teacher));
            Teacher teacherEntity = teacherProducer.update(teacher);
            res.setDesc("Success");
            res.setTeacher(teacherEntity);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.DELETE)
    public ResponseEntity<TeacherResponse> delete(@RequestBody TeacherRequest teacher) {
        TeacherResponse res = TeacherResponse.builder().build();
        try {
            log.info("DeleteTeacher: " + objectMapper.writeValueAsString(teacher));
            Teacher teacherEntity = teacherProducer.delete(teacher);
            res.setDesc("Success");
            res.setTeacher(teacherEntity);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
