package com.example.demo.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/lab")
@Controller
public class laboratory {

    @GetMapping("")
    @ResponseBody
    String showLab(){
        return "Hello Lab!";
    }
}
