package com.example.demo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    public String error1(Exception ex, Model model)
    {
        System.out.println("GlobalExceptionHandler FileNotFoundException.. ex : " +  ex);
        System.out.println("GlobalExceptionHandler FileNotFoundException.. ex");
        model.addAttribute("ex",ex);

        return "memo/error";
    }

    @ExceptionHandler(ArithmeticException.class)
    public String error2(Exception ex, Model model)
    {
        System.out.println("GlobalExceptionHandler ArithmeticException.. ex : " +  ex);
        System.out.println("GlobalExceptionHandler ArithmeticException.. ex ");
        model.addAttribute("ex",ex);

        return "memo/error";
    }

    @ExceptionHandler(Exception.class)
    public String error3(Exception ex, Model model)
    {
        System.out.println("GlobalExceptionHandler Exception.. ex : " +  ex);
        System.out.println("GlobalExceptionHandler Exception.. ex  ");
        model.addAttribute("ex",ex);

        return "memo/error";
    }
}
