package com.example.demo.Payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/verifyIamport")
public class VerifyController {

    @Value("${iamport.api.key}")
    // REST API 키
    private  String imp_key;

    @Value("${iamport.api.secret}")
    // REST API SECRET 키
    private  String imp_secret;

    // 토큰 발급
    public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    // 결제 취소
    public static final String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";

    private String accessToken;

    @GetMapping("/getToken")
    public String getTokenForm() {
        return "/verifyIamport/getToken"; // Thymeleaf 템플릿 이름
    }

    @PostMapping("/getToken")
    public String getToken(Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 아임포트 API에 전송할 요청 데이터 설정
        String requestBody = "{\"imp_key\":\"" + imp_key + "\",\"imp_secret\":\"" + imp_secret + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            RestTemplate restTemplate = new RestTemplate();
            // 아임포트 API 호출
            ResponseEntity<String> response = restTemplate.postForEntity(IMPORT_TOKEN_URL, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();

                // JSON 파싱 accessToken 만 출력
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                accessToken= jsonNode.get("response").get("access_token").asText();

                model.addAttribute("result", responseBody);

                System.out.println("access_token: " + accessToken);

            } else {
                model.addAttribute("error", "아임포트 토큰 발급 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            model.addAttribute("error", "아임포트 토큰 발급 중 오류 발생");
        }

        return "/verifyIamport/getToken"; // Thymeleaf 템플릿 이름
    }


    @GetMapping("/cancel")
    public String refound(){

        // URL

        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        //PARAMETER
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("merchant_uid","018ab533-7086-1167-d9c0-c188bf8f3c1c");

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(IMPORT_CANCEL_URL, HttpMethod.POST,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return "/index";

    }

}


