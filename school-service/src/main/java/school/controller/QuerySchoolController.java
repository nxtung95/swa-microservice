package school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import school.entity.School;
import school.service.QuerySchoolService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class QuerySchoolController {
    @Autowired
    private QuerySchoolService querySchoolService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/schools/{id}", method = RequestMethod.GET)
    public ResponseEntity<School> view(@PathVariable(value = "id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            School school = querySchoolService.findSchoolById(id);
            log.info("ViewSchool: " + objectMapper.writeValueAsString(school));
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
