package com.example.appspringatmsystem.payload;

import com.example.appspringatmsystem.entity.enums.MoneyBoxType;
import com.example.appspringatmsystem.entity.enums.MoneyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeDto {
    private MoneyBoxType moneyBoxType;

    private Integer count;


}
