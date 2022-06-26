package com.example.appspringatmsystem.controller;

import com.example.appspringatmsystem.payload.CardDto;
import com.example.appspringatmsystem.payload.RechargeDto;
import com.example.appspringatmsystem.payload.response.ApiResponse;
import com.example.appspringatmsystem.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    Base64.Decoder decoder;
    @Autowired
    CardService cardService;

    @PostMapping
    public HttpEntity<?> addCard(@RequestBody CardDto cardDto){
        ApiResponse apiResponse = cardService.addCard(cardDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/withdraw/{amount}")
    public HttpEntity<?> withdrawFromCard(@PathVariable Double amount,HttpServletRequest httpServletRequest){
        String authorization = httpServletRequest.getHeader("Authorization");
        ApiResponse apiResponse = cardService.withdrawFromCard(authorization,amount);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}
