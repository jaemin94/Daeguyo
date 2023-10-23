package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.mapper.ResMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResService {

    @Autowired
    private ResMapper mapper;

    public List<ResDto> searchAll(){
        ResDto dto = new ResDto();
        dto.getKeywords();

        return mapper.selectAll();

    }
    public List<ResDto> searchCatagory(String food_catagory) {
        ResDto dto = new ResDto();
        dto.getKeywords();

        return mapper.selectCatagory(food_catagory);
    }


    // review_tab 에서 특정 식당 DTO 출력해야
    public ResDto searchOne(String res_id){

        return  mapper.selectOne(res_id);
    }

}

