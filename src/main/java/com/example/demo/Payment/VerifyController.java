package com.example.demo.Payment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

    // 토큰 생성후 토큰 저장 v1
    @PostMapping("/getToken2")
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

        return "/verifyIamport/getToken2"; // Thymeleaf 템플릿 이름
    }


    // v2 토큰 생성후 토큰 저장
    @PostMapping("/getToken")
    public String getToken2(Model model){
        // URL
        String apiKey = "zUj32nCXlaAWMEkzbrRFjpzpkb6cLycYqpTo9Insnq5vxvzsbzTKK7mYTlCqAj7cO5TWn89ag6kQstyH";
        String apiUrl = "https://api.portone.io/login/api-key";

        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //PARAMETER
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("apiKey", apiKey);


        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // POST 요청 보내기
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
        // 응답 출력
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            model.addAttribute("result", responseBody);
            System.out.println("API 응답: " + responseBody);
            try {
                // JSON 문자열을 파싱하는 ObjectMapper 생성
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                // "accessToken" 필드의 값을 추출
                String accessToken = jsonNode.get("accessToken").asText();

                // 추출한 accessToken을 변수에 저장
                System.out.println("AccessToken: " + accessToken);

                // 이제 accessToken을 사용하거나 저장할 수 있습니다.
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", "아임포트 토큰 발급 중 오류 발생");
            }

        } else {
            System.err.println("API 호출 실패: " + responseEntity.getStatusCode());
            model.addAttribute("error", "아임포트 토큰 발급 실패: " + responseEntity.getStatusCode());
        }

         // Thymeleaf 템플릿 이름

        return "/verifyIamport/getToken";
    }



    // 주문 단건 조회
    @GetMapping("/searchOne")
    public String searchOne(String payment_id){
        // URL
        payment_id = "paymentId_1695257161639";
        String url = "https://api.portone.io/v2/payments" + payment_id;

        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        //PARAMETER
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("payment_id",payment_id);

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return "/index";
    }

    // 결제 상태 조회
    @GetMapping("/checkStatus")
    public String checkStatus(){

        // URL

        String url = "https://api.iamport.kr/payments/status/all?page=1&limit=20&sorting=-started";

        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        //PARAMETER
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("payment_status","all");

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return "/index";
    }


    // 결제 취소
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


