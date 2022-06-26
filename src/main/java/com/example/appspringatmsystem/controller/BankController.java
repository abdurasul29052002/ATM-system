package com.example.appspringatmsystem.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank")
public class BankController {
    @PostMapping
    public HttpEntity<?> addBank(){
        return  null;
    }
}
