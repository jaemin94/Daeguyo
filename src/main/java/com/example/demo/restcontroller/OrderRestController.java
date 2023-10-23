package com.example.demo.restcontroller;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.OrderService;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@Slf4j
@RequestMapping()
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orderSuccess")
    public void orderSuccess(@RequestBody OrderDto dto){
        log.info("dto : " + dto);
        List<String> menuNames = Arrays.asList(dto.getMenu_name().split(","));
        List<String> selectedOptions = Arrays.asList(dto.getSelect_option().split(","));

        for(int i =0; i<menuNames.size(); i++){
            for (String menuName : menuNames) {
                for (String selectedOption : selectedOptions) {
                    dto.setSelect_option(selectedOption);
                }
                dto.setOrder_id(dto.getOrder_id() + i);
                dto.setMenu_name(menuName);
                orderService.addOrder(dto);
            }
            i++;
        }
    }


    @DeleteMapping("/deleteOrder/{order_id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String order_id) {
        orderService.deleteOrder(order_id);
        return ResponseEntity.noContent().build();
    }

}

