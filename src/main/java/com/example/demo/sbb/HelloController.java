package com.example.demo.sbb;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/session")
    @ResponseBody
    public String hello(HttpServletRequest req){

        return req.getSession().toString();
    }

}