package com.tuespot.gateway.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FallBackMethodController {

    @GetMapping("/userfallback")
    public String userFallBack(){
        return "Please try later";
    }

    @GetMapping("/departmentfallback")
    public String departmentFallBack(){
        return "Please try later";
    }

}
