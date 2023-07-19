package school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import school.entity.School;
import school.service.CommandSchoolService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandSchoolController {
    @Autowired
    private CommandSchoolService commandSchoolService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/schools/", method = RequestMethod.POST)
    public ResponseEntity<School> add(@RequestBody School school) {
        try {
            School resSchool = commandSchoolService.save(school);
            log.info("AddSchool: " + objectMapper.writeValueAsString(resSchool));
            if (Objects.isNull(resSchool)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(resSchool);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/schools/", method = RequestMethod.PUT)
    public ResponseEntity<School> update(@RequestBody School school) {
        try {
            School resSchool = commandSchoolService.update(school);
            log.info("UpdateSchool: " + objectMapper.writeValueAsString(resSchool));
            if (Objects.isNull(resSchool)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(resSchool);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/schools/", method = RequestMethod.DELETE)
    public ResponseEntity<School> delete(@RequestBody School school) {
        try {
            commandSchoolService.delete(school);
            log.info("DeleteUser: " + objectMapper.writeValueAsString(school));
            if (Objects.isNull(school)) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(school);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
