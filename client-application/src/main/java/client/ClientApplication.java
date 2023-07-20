package client;

import client.object.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class ClientApplication {
    private static final Logger logger = LogManager.getLogger(ClientApplication.class);

    private static final String API_GATEWAY = "http://localhost:9000";
//    private static RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);

        System.out.println("--------- START Login student -----------------------");
        LoginRequest loginRequest = new LoginRequest("student@gmail.com", "test1234");
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        LoginResponse result = restTemplate.postForObject(API_GATEWAY + "/authen-service/api/v1/login", request, LoginResponse.class);
        System.out.println("Response login: " + objectMapper.writeValueAsString(result));
        System.out.println("--------- END Login student -----------------------");

        System.out.println("--------- START Add Student -----------------------");
        String token = result.getToken();
        List<Reward> rewardsList = new ArrayList<>();
        rewardsList.add(Reward.builder()
                        .id("52b228bc-bc8e-4335-a563-b4df576de312")
                        .name("reward1")
                        .quantity("1")
                        .type("Gift")
                        .price(60)
                .build());
        rewardsList.add(Reward.builder()
                .id("52b228bc-bc8e-4335-a563-b4df576de313")
                .name("reward2")
                .quantity("1")
                .type("Inschool")
                .price(40)
                .build());
        List<Element> elements = new ArrayList<>();
        elements.add(Element.builder()
                        .id("52b228bc-bc8e-4335-a563-b4df576de3d2")
                        .type("head")
                        .price(20)
                .build());
        elements.add(Element.builder()
                .id("52b228bc-bc8e-4335-a563-b4df576de333")
                .type("hair")
                .price(30)
                .build());
        Avatar avatar = Avatar.builder()
                .id("52b228bc-bc8e-4335-a563-b4df576de3d1")
                .elements(elements)
                .build();
        StudentRequest studentRequest = StudentRequest.builder()
                .firstName("student")
                .lastName("a")
                .studentNumber(1)
                .school(School.builder()
                        .id("52b228bc-bc8e-4335-a563-b4df576de314")
                        .name("School 1")
                        .phone("09123432")
                        .address("School Address 1")
                        .build())
                .classStudent(StudentClass.builder()
                        .year(2023)
                        .group("A")
                        .build())
                .score(1000)
                .rewards(rewardsList)
                .avatar(avatar)
                .build();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<StudentRequest> addStudentRq = new HttpEntity<>(studentRequest, headers);

        StudentResponse studentResponse = restTemplate.postForObject(API_GATEWAY + "/student-service/api/v1/students", addStudentRq, StudentResponse.class);
        System.out.println("Response : " + objectMapper.writeValueAsString(studentResponse));
        System.out.println("--------- END Add Student -----------------------");

        Thread.sleep(1000);

        System.out.println("--------- START View Student -----------------------");
        HttpEntity<String> rq = new HttpEntity<>(headers);
        Student viewStudent = restTemplate.exchange(API_GATEWAY + "/student-service/api/v1/students/" + studentResponse.getStudent().getId(), HttpMethod.GET, rq, Student.class).getBody();
        System.out.println("Response : " + objectMapper.writeValueAsString(viewStudent));
        System.out.println("--------- END View Student -----------------------");

        System.out.println("--------- START Update Student -----------------------");
        studentRequest.setId(studentRequest.getId());
        studentRequest.setFirstName("changefirstName");
        studentRequest.setLastName("changeLastname");
        HttpEntity<StudentRequest> updateRq = new HttpEntity<>(studentRequest, headers);
        StudentResponse updateRes = restTemplate.exchange(API_GATEWAY + "/student-service/api/v1/students", HttpMethod.PUT, updateRq, StudentResponse.class).getBody();
        System.out.println("Response : " + objectMapper.writeValueAsString(updateRes));
        System.out.println("--------- END Update Student -----------------------");

        Thread.sleep(500);

        System.out.println("--------- START DELETE Student -----------------------");
        studentRequest.setFirstName("changefirstName");
        studentRequest.setLastName("changeLastname");
        HttpEntity<String> deleteRq = new HttpEntity<>(headers);
//        restTemplate.getRequestFactory().
        StudentResponse deleteRes = restTemplate.exchange(API_GATEWAY + "/student-service/api/v1/students/" + studentResponse.getStudent().getId(), HttpMethod.DELETE, deleteRq, StudentResponse.class).getBody();
        System.out.println("Response : " + objectMapper.writeValueAsString(deleteRes));
        System.out.println("--------- END DELETE Student -----------------------");
    }

}
