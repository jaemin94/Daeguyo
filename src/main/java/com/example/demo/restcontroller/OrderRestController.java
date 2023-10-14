package com.example.demo.restcontroller;

import com.example.demo.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping()
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @DeleteMapping("/deleteOrder/{order_id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String order_id) {
        orderService.deleteOrder(order_id);
        return ResponseEntity.noContent().build();
    }

}
