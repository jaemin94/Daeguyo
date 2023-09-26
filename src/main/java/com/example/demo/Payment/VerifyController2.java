package com.example.demo.Payment;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/verifyIamport2")
public class VerifyController2 {

    @Value("${iamport2.api.key}")
    // REST API 키
    private  String apiKey;

    // 토큰 발급
    public static final String IMPORT_TOKEN_URL="https://api.portone.io/login/api-key";


    private String accessToken;

    @GetMapping("/getToken")
    public String getTokenForm() {
        return "/verifyIamport2/getToken"; // Thymeleaf 템플릿 이름
    }


    // v2 토큰 생성후 토큰 저장
    @PostMapping("/getToken")
    public String getToken2(Model model){
        // URL

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
        ResponseEntity<String> responseEntity = restTemplate.exchange(IMPORT_TOKEN_URL, HttpMethod.POST, entity, String.class);
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
                accessToken = jsonNode.get("accessToken").asText();

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

        return "/verifyIamport2/getToken";
    }


    // 주문 단건 조회
    @GetMapping("/searchOne")
    public String searchOne(String payment_id){
        // URL
        payment_id = "paymentId_1695257161639";
        String url = "https://api.portone.io/v2/payments/" + payment_id;

        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        //PARAMETER
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("payment_id",payment_id);

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return "/index";
    }

    // 주문 전체 조회
    @GetMapping("/searchAll")
    public String searchAll(){
        // URL

        String url = "https://api.portone.io/v2/payments?sort_by=REQUESTED_AT&sort_order=DESCENDING";

        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        //PARAMETER
//        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//        params.add("payment_id",payment_id);

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return "/index";
    }


//     결제 취소 아직 미완성
        @GetMapping("/cancel")
        public String refound(){

            // URL
            String payment_id = "paymentId1695699982554";
            String IMPORT_CANCEL_URL = "https://api.portone.io/v2/payments/"+payment_id+"/cancel";
            //HEADER
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            headers.add("Content-Type", "application/json");
            //PARAMETER
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("reason", "Refund reason goes here"); // 환불 이유

            // 요청 본문을 JSON 형식으로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBodyJson;
            try {
                requestBodyJson = objectMapper.writeValueAsString(requestBody);
            } catch (JsonProcessingException e) {
                // JSON 변환 오류 처리
                e.printStackTrace();
                return "/index";
            }

            // HEADER 와 PARAMETER를 합치는 작업
            HttpEntity<String> entity = new HttpEntity<>(requestBodyJson, headers);

            //REQUEST_CASE1
            RestTemplate rt = new RestTemplate();
            ResponseEntity<String> response = rt.exchange(IMPORT_CANCEL_URL, HttpMethod.POST,entity,String.class);
            System.out.println(response);
            System.out.println(response.getBody());

            return "/index";

        }
//

}


