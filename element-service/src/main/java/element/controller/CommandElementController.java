package element.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import element.entity.Element;
import element.enums.ElementTypeEnum;
import element.object.ElementRequest;
import element.object.ElementResponse;
import element.producer.ElementProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandElementController {
    @Autowired
    private ElementProducer elementProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/elements", method = RequestMethod.POST)
    public ResponseEntity<ElementResponse> add(@RequestBody ElementRequest element) {
        ElementResponse res = ElementResponse.builder().build();
        ElementTypeEnum elementType = ElementTypeEnum.of(element.getType());
        if (elementType == null) {
            res.setDesc("Element type is invalid, please try again.");
            return ResponseEntity.badRequest().body(res);
        }
        if (element.getPrice() < 10 || element.getPrice() > 50) {
            res.setDesc("Element price have to greater than 50 and less than 10, please try again.");
            return ResponseEntity.badRequest().body(res);
        }
        try {
            element.setId(UUID.randomUUID().toString());
            log.info("AddElement: " + objectMapper.writeValueAsString(element));
            Element rp = elementProducer.save(element);
            res.setDesc("Success");
            res.setElement(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/elements", method = RequestMethod.PUT)
    public ResponseEntity<ElementResponse> update(@RequestBody ElementRequest element) {
        ElementResponse res = ElementResponse.builder().build();
        ElementTypeEnum elementType = ElementTypeEnum.of(element.getType());
        if (elementType == null) {
            res.setDesc("Element type is invalid, please try again.");
            return ResponseEntity.badRequest().body(res);
        }
        if (element.getPrice() < 10 || element.getPrice() > 50) {
            res.setDesc("Element price have to greater than 50 and less than 10, please try again.");
            return ResponseEntity.badRequest().body(res);
        }
        try {
            log.info("UpdateElement: " + objectMapper.writeValueAsString(element));
            Element rp = elementProducer.update(element);
            res.setDesc("Success");
            res.setElement(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/elements", method = RequestMethod.DELETE)
    public ResponseEntity<ElementResponse> delete(@RequestBody ElementRequest element) {
        ElementResponse res = ElementResponse.builder().build();
        try {
            log.info("DeleteElement: " + objectMapper.writeValueAsString(element));
            Element rp = elementProducer.delete(element);
            res.setDesc("Success");
            res.setElement(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
