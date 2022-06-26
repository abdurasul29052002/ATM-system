package com.example.appspringatmsystem.controller;

import com.example.appspringatmsystem.payload.ATMDto;
import com.example.appspringatmsystem.payload.RechargeDto;
import com.example.appspringatmsystem.payload.response.ApiResponse;
import com.example.appspringatmsystem.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    @Autowired
    ATMService atmService;

    @PostMapping
    public HttpEntity<?> addAtm(@RequestBody ATMDto atmDto){
        ApiResponse apiResponse = atmService.addAtm(atmDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/recharge/{id}")
    public HttpEntity<?> rechargeAtm(@PathVariable Integer id, @RequestBody RechargeDto rechargeDto){
        ApiResponse apiResponse = atmService.rechargeAtm(id,rechargeDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAtm(@PathVariable Integer id){
        return atmService.getAtm(id);
    }
}
