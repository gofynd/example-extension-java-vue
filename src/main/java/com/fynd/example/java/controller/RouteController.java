package com.fynd.example.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@CrossOrigin(origins = "*")
public class RouteController {

    @RequestMapping("/company/{company_id}")
    public String redirect() {
            return "forward:/";
    }

}
