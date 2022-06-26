package com.example.appspringatmsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double commissionAmountOurCards;

    private Double commissionAmountOtherCards;

    @OneToMany(mappedBy = "bank")
    private List<Card> cardList;
}
