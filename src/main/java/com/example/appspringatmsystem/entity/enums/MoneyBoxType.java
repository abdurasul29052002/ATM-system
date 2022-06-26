package com.example.appspringatmsystem.entity.enums;

public enum MoneyBoxType {
    ONE(MoneyType.DOLLAR, 1),
    FIVE(MoneyType.DOLLAR, 5),
    TEN(MoneyType.DOLLAR, 10),
    TWENTY(MoneyType.DOLLAR, 20),
    FIFTY(MoneyType.DOLLAR, 50),
    HUNDRED(MoneyType.DOLLAR, 100),
    THOUSAND(MoneyType.SUM, 1_000),
    FIVE_THOUSAND(MoneyType.SUM, 5_000),
    TEN_THOUSAND(MoneyType.SUM, 10_000),
    FIFTY_THOUSAND(MoneyType.SUM, 50_000),
    HUNDRED_THOUSAND(MoneyType.SUM, 100_000);

    private final MoneyType moneyType;
    private final Integer intValue;

    MoneyBoxType(MoneyType moneyType, Integer intValue) {
        this.moneyType=moneyType;
        this.intValue = intValue;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public Integer getIntValue() {
        return intValue;
    }
}
