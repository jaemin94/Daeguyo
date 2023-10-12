package com.example.demo;

import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderTest {
    @Autowired
    public OrderMapper orderMapper;

    @Test
    public void selectOneTest() {
        String orderId= "2";
        OrderDto order = orderMapper.selectOne(orderId);
        log.info("Found Order: " + order);

    }

    @Test
    public void selectAllTest() {
        orderMapper.selectAll().stream().map(OrderDto::toString).forEach(log::info);
    }
}
