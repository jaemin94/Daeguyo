package com.example.demo.C04NaverAPI;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/th/naver")
public class NaverSearchAPIController {

    @Value("${naver-cloud-sms.accessKey}")
    private String Client_Id;

    @Value("${naver-cloud-sms.secretKey}")
    private String Client_Secret;

    @GetMapping("/local/{page}/{keyWord}")
    public @ResponseBody String  local(@PathVariable String page, @PathVariable  String keyWord){
        log.info("GET/th/naver/local");

        // URL
        String url ="https://openapi.naver.com/v1/search/local.json?query="+keyWord+"&display=10&start=1&sort=random";
        //HEADER
        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Naver-Client-Id",Client_Id);
        headers.add("X-Naver-Client-Secret",Client_Secret);
        //PARAMETER
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return response.getBody();

    }
    @GetMapping("/image/{page}/{keyWord}")
    public @ResponseBody String  image(@PathVariable String page, @PathVariable  String keyWord){
        log.info("GET/th/naver/image");

        // URL
        String url ="https://openapi.naver.com/v1/search/image?query="+keyWord+"&display=10&start=1";
        //HEADER
        HttpHeaders headers = new HttpHeaders();

        headers.add("X-Naver-Client-Id",Client_Id);
        headers.add("X-Naver-Client-Secret",Client_Secret);
        //PARAMETER
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return response.getBody();

    }
}
