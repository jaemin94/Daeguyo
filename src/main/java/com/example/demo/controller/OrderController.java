package com.example.demo.controller;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.ResDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.OrderService;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/orderStatus")
    public void getOrderStatus() {

    }

//    @GetMapping("/orderCheck")
//    public String resOrderCheck(Model model,@RequestParam(name = "order_id", required = false) String order_id) {
//        log.info("Get/restList called");
////          List<OrderDto>  orderdto = orderService.getOrder();
////            System.out.println(orderdto);
////            model.addAttribute("orderlist", orderdto);
//        OrderDto orderDto = new OrderDto();
//        List<OrderDto> orderlist;
//        OrderDto dto;
//        UserDto userdto;
//        if(order_id != null) {
//            dto = orderService.getSearchOrder(order_id);
//            String u_email = dto.getU_email();
//            userdto = userService.SearchUser(u_email);
//            System.out.println("dto: "+ dto);
//            System.out.println( "userdto: " +userdto);
//            model.addAttribute("orderdto",dto);
//            model.addAttribute("userdto",dto);
//
//        }else {
//            orderlist = orderService.getOrder();
//            System.out.println(orderlist);
//            model.addAttribute("orderlist",orderlist);
//
//        }
//        return "resCheck";
//
//    }

    @GetMapping("/orderCheck1")
    public String resOrderCheck1(Model model,@RequestParam(name = "order_id", required = true) String order_id) {
        log.info("Get/orderCheck1 called");
        OrderDto dto = new OrderDto();
        List<OrderDto> orderlist;
        UserDto userdto;
        dto.setOrder_id(order_id);
            dto = orderService.getSearchOrder(order_id);
            String u_email = dto.getU_email();
            System.out.println(u_email);
            userdto = userService.SearchUser(u_email);
            System.out.println("dto: "+ dto);
            System.out.println("userdto: "+ userdto);
            System.out.println("order_id: "+ order_id);

            model.addAttribute("order_id", order_id);
            model.addAttribute("orderdto",dto);
            model.addAttribute("userdto",userdto);


        return "resCheck";

    }
    @GetMapping("/orderCheck")
    public String resOrderCheck(Model model,@RequestParam(name = "order_id", required = false) String order_id) {
        log.info("Get/orderCheck called");
          List<OrderDto>  orderdto = orderService.getOrder();
            System.out.println(orderdto);
            model.addAttribute("order_id", order_id);
            model.addAttribute("orderlist", orderdto);

        return "resCheck";

    }

}
