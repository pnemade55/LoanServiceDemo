package com.ing.ls.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(value = "/ping")
    public String ping(){
        return "App is running fine!";

    }

}
