package com.example.appspringatmsystem.service;

import com.example.appspringatmsystem.entity.ATM;
import com.example.appspringatmsystem.entity.Address;
import com.example.appspringatmsystem.entity.Bank;
import com.example.appspringatmsystem.entity.MoneyBox;
import com.example.appspringatmsystem.entity.enums.MoneyBoxType;
import com.example.appspringatmsystem.payload.ATMDto;
import com.example.appspringatmsystem.payload.RechargeDto;
import com.example.appspringatmsystem.payload.response.ApiResponse;
import com.example.appspringatmsystem.repository.ATMRepository;
import com.example.appspringatmsystem.repository.BankRepository;
import com.example.appspringatmsystem.repository.MoneyBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ATMService {

    @Autowired
    BankRepository bankRepository;
    @Autowired
    ATMRepository atmRepository;
    @Autowired
    MoneyBoxRepository moneyBoxRepository;

    public ApiResponse addAtm(ATMDto atmDto) {
        Optional<Bank> optionalBank = bankRepository.findById(atmDto.getBankId());
        if (optionalBank.isEmpty())
            return new ApiResponse("Bank not found",false);
        ATM atm = new ATM();

        Address address = new Address();
        address.setStreet(atmDto.getStreet());
        address.setCity(atmDto.getCity());

        atm.setAddress(address);
        atm.setBank(optionalBank.get());
        atm.setCardType(atmDto.getCardType());
        atm.setMaxLimitAmount(atmDto.getMaxLimitAmount());
        atm.setMinLimitBalance(atmDto.getMinLimitBalance());
        atm.setMoneyBoxes(new ArrayList<>());
        for (MoneyBoxType moneyBoxType : atmDto.getMoneyBoxTypes()) {
            MoneyBox moneyBox = new MoneyBox();
            moneyBox.setMoneyBoxType(moneyBoxType);
            atm.getMoneyBoxes().add(moneyBox);
            moneyBox.setAtm(atm);
            moneyBoxRepository.save(moneyBox);
        }
        return new ApiResponse("ATM saved",true);
    }

    public ApiResponse rechargeAtm(Integer id, RechargeDto rechargeDto) {
        Optional<ATM> optionalATM = atmRepository.findById(id);
        if (optionalATM.isEmpty())
            return new ApiResponse("ATM not found",false);
        ATM atm = optionalATM.get();
        for (MoneyBox moneyBox : atm.getMoneyBoxes()) {
            if (rechargeDto.getMoneyBoxType().equals(moneyBox.getMoneyBoxType())) {
                moneyBox.setCount(rechargeDto.getCount());
                atm.setCurrentBalance(rechargeDto.getCount()*rechargeDto.getMoneyBoxType().getIntValue().doubleValue());
            }
        }
        atmRepository.save(atm);
        return new ApiResponse("Recharged",true);
    }

    public HttpEntity<?> getAtm(Integer id) {
        Optional<ATM> optionalATM = atmRepository.findById(id);
        return ResponseEntity.status(optionalATM.isPresent()?200:409).body(optionalATM.orElse(null));
    }
}
