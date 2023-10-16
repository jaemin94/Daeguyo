package com.example.demo.restcontroller;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.OrderService;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping()
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping ("/orderCheck1")
    public String resOrderCheck(Model model,@RequestBody OrderDto orderDto){
        log.info("Post/restList called");
        String order_id = orderDto.getOrder_id();
        log.info("Order_id : " + order_id);
        List<OrderDto> orderlist;
        OrderDto dto;
        UserDto userdto;
        if(order_id != null) {
            dto = orderService.getSearchOrder(order_id);
            String u_email = dto.getU_email();
            userdto = userService.SearchUser(u_email);
            System.out.println("dto: "+ dto);
            System.out.println( "userdto: " +userdto);
            model.addAttribute("orderdto",dto);
            model.addAttribute("userdto",dto);

        }else if (order_id == null){
            orderlist = orderService.getOrder();
            System.out.println(orderlist);
            model.addAttribute("orderlist",orderlist);

        }

       return "resCheck";
    }
}
