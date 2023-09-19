package com.example.demo.C03KakaoAPI;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("/th/kakao/pay")
public class C05KakaoPayAPI {

    private final String admin_key="507a4340cbf928f86fdac0d61d7f8c3d";

    @GetMapping("/index")
    public void index(){
        log.info("GET/th/kakao/pay/index");
    }

    @GetMapping("/payment")
    public @ResponseBody PaymentResponse payment(){
        log.info("GET/th/kakao/pay/payment");

        // URL
        String url = "https://kapi.kakao.com/v1/payment/ready";

        //HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK "+admin_key);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid","TC0ONETIME");
        params.add("partner_order_id","partner_order_id");
        params.add("partner_user_id","partner_user_id");
        params.add("item_name","초코파이");
        params.add("quantity","1");
        params.add("total_amount","2200");
        params.add("vat_amount","200");
        params.add("tax_free_amount","0");
        params.add("approval_url","http://localhost:8082/th/kakao/pay/success");
        params.add("fail_url","http://localhost:8082/th/kakao/pay/fail");
        params.add("cancel_url","http://localhost:8082/th/kakao/pay/cancel");

        //header + parameter
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params,headers);

        //Request_Case1
        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response =  rt.exchange(url, HttpMethod.POST,entity,String.class);
//        System.out.println(response);
        PaymentResponse response= rt.postForObject(url,entity,PaymentResponse.class);


        return response;
    }

    @GetMapping("/success")
    public @ResponseBody String success(){
        log.info("GET /th/kakao/pay/success");
        return "결제 성공!";
    }
    @GetMapping("/cancel")
    public @ResponseBody String cancel(){
        log.info("GET /th/kakao/pay/cancel");
        return "결제 취소!";
    }
    @GetMapping("/fail")
    public @ResponseBody String fail(){
        log.info("GET /th/kakao/pay/fail");
        return "결제 실패!";
    }

}

@Data
class PaymentResponse {
    private String tid;
    private boolean tms_result;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private String created_at;
}