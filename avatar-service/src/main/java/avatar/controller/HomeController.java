package avatar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1")
public class HomeController {
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public void healthCheck() {
		log.info("Health check1!!!");
		log.info("Health check2!!!");
		log.info("Health check3!!!");
	}
}
