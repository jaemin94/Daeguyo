package com.example.demo.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    // REST API 키
    private final String imp_key ="5003343884365611";

    // REST API SECRET 키
    private final String imp_secret ="uDJBXBctWIj05tSeQVyhBdGxwBe97WTyjucHQoDyc16sWUxOOhy4bP2v7QpFTLwGfwUcvPC2l264cgWi";

    // 토큰 발급
    public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    // 결제 취소
    public static final String IMPORT_CANCEL_URL = "https://api.iamport.kr/payments/cancel";


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
                model.addAttribute("result", responseBody);
            } else {
                model.addAttribute("error", "아임포트 토큰 발급 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            model.addAttribute("error", "아임포트 토큰 발급 중 오류 발생");
        }

        return "/verifyIamport/getToken"; // Thymeleaf 템플릿 이름
    }

}
