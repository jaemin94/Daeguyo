package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.MenuDto;
import com.example.demo.domain.mapper.MenuMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuMapper mapper;

    //review_tab.page1 메뉴 가져오기
    public List<MenuDto> getResMenu(String res_id) {
        log.info("MenuService's menu search at res_id" + res_id);
        List<MenuDto> menu = mapper.ResMenu(res_id); // res_id로 메뉴 검색
        for (MenuDto menuDto : menu) {
            String optionsJson = menuDto.getOptions();
            List<Map<String, Map<String, String>>> options = parseOptions(optionsJson); // 옵션 파싱
            menuDto.setParsedOptions(options); // MenuDto에 옵션 정보 set
        }
        return menu;
    }

    //위의 getResMenu에서 옵션 파싱 도와줄 함수
    private List<Map<String, Map<String, String>>> parseOptions(String optionsJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {// 옵션 자료 타입 변환
            return objectMapper.readValue(optionsJson, new TypeReference<List<Map<String, Map<String, String>>>>() {});
        } catch (Exception e) {
            log.error("Error parsing options JSON", e);
            return Collections.emptyList();
        }
    }

    public List<MenuDto> getRes_id1(String menu_id){

       return mapper.findResIdByMenuId1(menu_id);
    }

    public String getRes_id(String menu_id){

        return mapper.findResIdByMenuId(menu_id);
    }

    public List<MenuDto> getAllMenuFromCart(String menu_id){

        return mapper.selectOne(menu_id);
    }

}
