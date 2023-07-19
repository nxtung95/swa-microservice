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
import school.object.SchoolRequest;
import school.object.SchoolResponse;
import school.producer.SchoolProducer;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandSchoolController {
    @Autowired
    private SchoolProducer schoolProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/schools", method = RequestMethod.POST)
    public ResponseEntity<SchoolResponse> add(@RequestBody SchoolRequest school) {
        SchoolResponse res = SchoolResponse.builder().build();
        try {
            school.setId(UUID.randomUUID().toString());
            log.info("AddSchool: " + objectMapper.writeValueAsString(school));
            schoolProducer.save(school);
            res.setId(school.getId());
            res.setDesc("Success");
            res.setAddress(school.getAddress());
            res.setEmail(school.getEmail());
            res.setName(school.getName());
            res.setPhone(school.getPhone());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/schools", method = RequestMethod.PUT)
    public ResponseEntity<SchoolResponse> update(@RequestBody SchoolRequest school) {
        SchoolResponse res = SchoolResponse.builder().build();
        try {
            log.info("UpdateSchool: " + objectMapper.writeValueAsString(school));
            schoolProducer.update(school);
            res.setId(school.getId());
            res.setDesc("Success");
            res.setAddress(school.getAddress());
            res.setEmail(school.getEmail());
            res.setName(school.getName());
            res.setPhone(school.getPhone());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/schools", method = RequestMethod.DELETE)
    public ResponseEntity<SchoolResponse> delete(@RequestBody SchoolRequest school) {
        SchoolResponse res = SchoolResponse.builder().build();
        try {
            log.info("DeleteSchool: " + objectMapper.writeValueAsString(school));
            schoolProducer.delete(school);
            res.setId(school.getId());
            res.setDesc("Success");
            res.setAddress(school.getAddress());
            res.setEmail(school.getEmail());
            res.setName(school.getName());
            res.setPhone(school.getPhone());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
