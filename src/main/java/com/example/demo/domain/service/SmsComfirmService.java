package com.example.demo.domain.service;

import com.example.demo.domain.dto.SmsComfirmDto;
import com.example.demo.domain.mapper.SmsComfirmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SmsComfirmService {

    @Autowired
    SmsComfirmMapper smsComfirmMapper;

    public SmsComfirmDto checkSms(String phone){

        return smsComfirmMapper.selectOne(phone);
    }

}
