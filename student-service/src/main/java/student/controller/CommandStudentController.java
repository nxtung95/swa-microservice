package student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student.entity.Student;
import student.object.*;
import student.producer.StudentProducer;
import student.service.QueryStudentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandStudentController {
    @Autowired
    private StudentProducer studentProducer;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private QueryStudentService queryStudentService;

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<StudentResponse> add(@RequestBody StudentRequest student) {
        StudentResponse res = StudentResponse.builder().build();
        try {
            student.setId(UUID.randomUUID().toString());
            log.info("AddStudent: " + objectMapper.writeValueAsString(student));
            Student s = studentProducer.save(student);
            res.setDesc("Success");
            res.setStudent(s);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public ResponseEntity<StudentResponse> update(@RequestBody StudentRequest student) {
        StudentResponse res = StudentResponse.builder().build();
        try {
            log.info("UpdateStudent: " + objectMapper.writeValueAsString(student));
            Student s = studentProducer.update(student);
            res.setDesc("Success");
            res.setStudent(s);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentResponse> delete(@PathVariable(value = "id") String id) {
        StudentResponse res = StudentResponse.builder().build();
        try {
            log.info("DeleteStudent: " + id);
            StudentRequest student = new StudentRequest();
            student.setId(id);
            Student s = studentProducer.delete(student);
            res.setDesc("Success");
            res.setStudent(s);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/buyElements", method = RequestMethod.POST)
    public ResponseEntity<BuyElementResponse> buyElements(@RequestBody BuyRemoveElementRequest request) {
        BuyElementResponse res = BuyElementResponse.builder().build();
        if (Strings.isNullOrEmpty(request.getStudentId()) || request.getElements() == null || request.getElements().isEmpty()) {
            res.setDesc("Invalid request input");
            return ResponseEntity.internalServerError().body(res);
        }
        try {
            log.info("BuyElement: " + objectMapper.writeValueAsString(request));
            studentProducer.buyElements(request);
            res.setDesc("Success");
            res.setStudentId(request.getStudentId());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/removeElements", method = RequestMethod.POST)
    public ResponseEntity<BuyElementResponse> removeElements(@RequestBody BuyRemoveElementRequest request) {
        BuyElementResponse res = BuyElementResponse.builder().build();
        if (Strings.isNullOrEmpty(request.getStudentId()) || request.getElements() == null || request.getElements().isEmpty()) {
            res.setDesc("Invalid request input");
            return ResponseEntity.internalServerError().body(res);
        }
        try {
            log.info("RemoveElement: " + objectMapper.writeValueAsString(request));
            studentProducer.removeElement(request);
            res.setDesc("Success");
            res.setStudentId(request.getStudentId());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/buyRewards", method = RequestMethod.POST)
    public ResponseEntity<BuyElementResponse> buyRewards(@RequestBody BuyRewardRequest request) {
        BuyElementResponse res = BuyElementResponse.builder().build();
        if (Strings.isNullOrEmpty(request.getStudentId()) || request.getRewards() == null || request.getRewards().isEmpty()) {
            res.setDesc("Invalid request input");
            return ResponseEntity.internalServerError().body(res);
        }
        try {
            String studentId = request.getStudentId();
            Student student = queryStudentService.findStudentById(studentId);
            if (student == null) {
                log.info("Dont exist student");
                res.setDesc("Dont exist student");
                return ResponseEntity.internalServerError().body(res);
            }
            List<Reward> currentRewards = student.getRewards();
            if (request.getRewards().stream().anyMatch(r -> currentRewards.stream().anyMatch(c -> c.getId().equals(r.getId())))) {
                log.info("Student exists reward");
                res.setDesc("Student exists an reward in list. Please check again.");
                return ResponseEntity.internalServerError().body(res);
            }
            log.info("BuyRewards: " + objectMapper.writeValueAsString(request));
            studentProducer.buyRewards(request);
            res.setDesc("Success");
            res.setStudentId(request.getStudentId());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
