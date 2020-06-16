package com.nevermind.springboot.lesson.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Value("${app.greeting.service.message}")
    private String greeting;

    public String greeting(){
        return greeting;
    }
}
