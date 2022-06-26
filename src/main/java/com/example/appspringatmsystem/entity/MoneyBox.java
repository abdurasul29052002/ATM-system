package com.example.appspringatmsystem.entity;

import com.example.appspringatmsystem.entity.enums.MoneyBoxType;
import com.example.appspringatmsystem.entity.enums.MoneyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MoneyBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private MoneyBoxType moneyBoxType;

    private Integer count=0;

    @JsonIgnore
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    private ATM atm;
}
