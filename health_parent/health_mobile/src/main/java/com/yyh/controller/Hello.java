package com.yyh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nihao")
public class Hello {
    @RequestMapping("/hello")
    public String hello() {
        return "hhhh";
    }
}
