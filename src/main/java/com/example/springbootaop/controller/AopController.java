package com.example.springbootaop.controller;


import com.example.springbootaop.annotation.Custom;
import com.example.springbootaop.vo.CustomVO;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class AopController {

    @Custom(firstValue = "A")
    @GetMapping("/test")
    public String test(CustomVO vo) {
        return "Hello World!";
    }
}