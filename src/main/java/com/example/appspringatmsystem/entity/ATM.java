package com.example.appspringatmsystem.entity;

import com.example.appspringatmsystem.entity.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CardType cardType;


    private Double maxLimitAmount;

    private Double minLimitBalance;

    private Double currentBalance=0.0;

    private boolean blocked=false;

    @OneToMany(mappedBy = "atm")
    private List<MoneyBox> moneyBoxes;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne
    private Bank bank;
}
