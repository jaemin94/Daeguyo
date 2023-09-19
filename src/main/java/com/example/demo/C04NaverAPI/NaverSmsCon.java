package com.example.demo.C04NaverAPI;

import com.example.demo.domain.dto.MessageDto;
import com.example.demo.domain.dto.SmsResponseDto;
import com.example.demo.domain.service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class NaverSmsCon {

    private final SmsService smsService;

    @PostMapping("/sms/send")
    public SmsResponseDto sendSms(@RequestBody MessageDto messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SmsResponseDto responseDto = smsService.sendSms(messageDto);
        return responseDto;
    }
}
