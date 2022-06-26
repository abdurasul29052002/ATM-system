package com.example.appspringatmsystem.payload;

import com.example.appspringatmsystem.entity.enums.CardType;
import com.example.appspringatmsystem.entity.enums.MoneyBoxType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ATMDto {
    private CardType cardType;

    private Double maxLimitAmount;

    private Double minLimitBalance;

    private Integer bankId;

    private boolean blocked;

    /* Fields need to MoneyBox */

    private List<MoneyBoxType> moneyBoxTypes;

    /* Fields need to Address */

    private String street;

    private String city;

}
