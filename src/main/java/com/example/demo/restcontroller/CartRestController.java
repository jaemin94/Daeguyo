package com.example.demo.restcontroller;

import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.daeguyo.MenuDto;
import com.example.demo.domain.mapper.MenuMapper;
import com.example.demo.domain.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @PostMapping("/updateOrder")
    @ResponseBody
    public void updateOrder(@RequestBody CartDto dto) {
        System.out.println(dto.getCount());
        cartService.updateOrderAmount(dto);


    }
  
    @PostMapping("/cart")
    public ResponseEntity<Map<String, Boolean>> addToCart(@RequestBody CartDto cartDto) {
        String cart_id = "cart_" + System.currentTimeMillis();
        cartDto.setCart_id(cart_id);
        boolean success = cartService.addToCart(cartDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteMenu")
    public int deleteMenu(@RequestParam("cart_id") String cart_id){
        log.info("cart_id: " +cart_id);
        return cartService.deleteFromCart(cart_id);

    }


}
