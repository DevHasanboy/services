package com.example.zipkin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping
    public String getMapping(){
        return "Welcome to zipkin";
    }

    @PostMapping("/send")
    public String postMessage(@RequestParam String message){
        return message;
    }
}
