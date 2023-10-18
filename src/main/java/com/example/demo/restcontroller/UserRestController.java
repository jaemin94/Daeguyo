package com.example.demo.restcontroller;


import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping()
public class UserRestController {

    @Autowired
    private UserService userService;

    @PutMapping("/memberUpdate")
    public int memberUpdate(@RequestBody UserDto dto){
        System.out.println(dto);
        return userService.memberUpdate(dto);


    }
    @DeleteMapping("/memberUpdate1")
    public int memberDelete(@RequestParam(required = false) String u_email){
        log.info("memberDelete : " + u_email);
        return userService.memberDelete(u_email);
    }

}
