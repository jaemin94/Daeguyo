package com.example.demo.C04NaverAPI;

import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.SmsComfirmDto;
import com.example.demo.domain.dto.SmsResponseDto;
import com.example.demo.domain.service.SmsComfirmService;
import com.example.demo.domain.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class NaverSmsCon {

    @Autowired
    private  SmsService smsService;
    @Autowired
    private SmsComfirmService smsComfirmService;

    @PostMapping("/sms/send")
    public SmsResponseDto sendSms(@RequestBody MessageDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SmsResponseDto responseDto = smsService.sendSms(messageDto);
        System.out.println("messageDto: " + messageDto.getTo());
        return responseDto;
    }

    @GetMapping("/checkPhone")
    public ResponseEntity<String> checkPhone(@RequestParam("phoneNum") String phone) {
        SmsComfirmDto dto = smsComfirmService.checkSms(phone);
        String serverVerificationCode = dto.getSmsComfirmnum();

        if (serverVerificationCode != null) {
            return ResponseEntity.ok(serverVerificationCode); // 인증 번호 반환
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 번호가 일치하지 않습니다.");
        }
    }

}
