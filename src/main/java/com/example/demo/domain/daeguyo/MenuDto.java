package com.example.demo.domain.daeguyo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    private String menu_id;
    private String res_id;
    private String menu_name;
    private int price;
    private String menu_detail;
//    private byte[] img;
    private String img;
    private String options;
    private String menu_catagory;

    // menu table의 options JSON 형태로 바꿨습니다
    // JSON값 key/ value로 나눠 사용하기 위해 아래 매서드들 추가

    private List<Map<String, Map<String, String>>> parsedOptions; // 새로운 필드

    public void setParsedOptions(List<Map<String, Map<String, String>>> parsedOptions) {
        this.parsedOptions = parsedOptions;
    }

}
