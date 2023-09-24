package com.example.demo.C03KakaoAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@Slf4j
@RequestMapping("/th/kakao")
public class C02KakaoLoginController {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String Client_code;

    private final String Redirect_url = "http://localhost:8080/th/kakao/callback";

    private final String logoutRedirect_uri = "http://localhost:8080/th/kakao/login";



    private KakaoTokenResponse kakaoTokenResponse;

    private KakaoProfile kakaoProfile;

    // 인가코드 요청
    @GetMapping("/getCode")
    public void autorize(HttpServletResponse resp) throws Exception{
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+Client_code+"&redirect_uri="+Redirect_url;
        resp.sendRedirect(url);
    }


    @GetMapping("/callback")
    public String redirectFunc(String code)
    {
        log.info("GET/th/kakao/callback's  CODE : " + code);
        // URL
        String url ="https://kauth.kakao.com/oauth/token";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        //PARAMETER
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",Client_code);
        params.add("redirect_uri",Redirect_url);
        params.add("code",code);
        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,headers);
        //REQUEST_CASE1
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST,entity,String.class);
//        System.out.println(response);
//        System.out.println(response.getBody());
        // JSON-> CLASS 반환 REQUEST_CASE2
        RestTemplate rt = new RestTemplate();
        KakaoTokenResponse resp = rt.postForObject(url,entity,KakaoTokenResponse.class);
        System.out.println(resp);
        this.kakaoTokenResponse = resp;


        return "redirect:/th/3ice/index";
    }

    @GetMapping("/main")
    public void main1(){

        log.info("GET/th/kakao/main");

    }

    @GetMapping("/login")
    public void login(){

        log.info("GET/th/kakao/login");

    }

    @GetMapping(value="/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody KakaoProfile profile() {
        log.info("GET/th/kakao/profile");
        // URL
        String url = "https://kapi.kakao.com/v2/user/me";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoTokenResponse.getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //PARAMETER

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        //REQUEST_CASE1
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST,entity,String.class);
//        System.out.println(response);
//        System.out.println(response.getBody());
        // JSON-> CLASS 반환 REQUEST_CASE2
        RestTemplate rt = new RestTemplate();
        KakaoProfile resp = rt.postForObject(url, entity, KakaoProfile.class);
        System.out.println(resp);
        System.out.println(resp.getProperties().getNickname());

        return resp;
    }

    // 자신한테 메시지
    // 01 scope=talk_message 권한 부여
    @GetMapping("/getCodeMsg")
    public void autorize_msg(HttpServletResponse resp) throws Exception{
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+Client_code+"&redirect_uri="+Redirect_url+"&scope=talk_message";
        resp.sendRedirect(url);
    }
    @GetMapping("/message/me/{message}")
    public Object sendMsg(@PathVariable("message") String message)
    {
        log.info("GET/th/kakao/message/me");
        // URL
        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoTokenResponse.getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //PARAMETER
        JSONObject template_object = new JSONObject();
        template_object.put("object_type","text");
        template_object.put("text",message);

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("template_object",template_object.toString());
        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());


        return response;
    }
    // 토큰 만료
    @GetMapping("/logout")
    public String logout(){
        log.info("GET/th/kakao/logout");
        // URL
        String url = "https://kapi.kakao.com/v1/user/logout";
        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoTokenResponse.getAccess_token());
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //PARAMETER

        // HEADER 와 PARAMETER를 합치는 작업
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        //REQUEST_CASE1
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST,entity,String.class);
        System.out.println(response);
        System.out.println(response.getBody());

        return "redirect:/th/kakao/logoutWithKakao";
    }

    @GetMapping("/logoutWithKakao")
    public void logoutWithKakao(HttpServletResponse resp) throws Exception {
        log.info("GET/th/kakao/logoutWithKakao");

        // URL
        String url = "https://kauth.kakao.com/oauth/logout?client_id="+Client_code+"&logout_redirect_uri="+logoutRedirect_uri;
        resp.sendRedirect(url);
    }




}


@Data
class KakaoTokenResponse {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
    private long refresh_token_expires_in;

}

@Data
class KakaoProfile {
    @JsonProperty("id")
    private long id;

    @JsonProperty("connected_at")
    private String connected_at;
    @JsonProperty("properties")
    private Properties properties;
    @JsonProperty("kakao_account")
    private Kakao_account kakao_account;

    @Data
    @NoArgsConstructor
    public static  class Kakao_account {
        private boolean profile_nickname_needs_agreement;
        private boolean profile_image_needs_agreement;
        private Profile profile;
        private boolean has_email;
        private boolean email_needs_agreement;
        private String is_email_valid;
        private String is_email_verified;
        private String email;
        private boolean has_age_range;
        private boolean age_range_needs_agreement;
        private String age_range;
    }
    @Data
    @NoArgsConstructor
    public static  class Profile {
        private String nickname;
        private String thumbnail_image_url;
        private String profile_image_url;
        private String is_default_image;

    }
    @Data
    @NoArgsConstructor
    public static  class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

}
