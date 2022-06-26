package com.example.appspringatmsystem.payload;

import com.example.appspringatmsystem.entity.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    @Size(max = 16,message = "Card number is not valid")
    private Long cardNumber;

    private LocalDate validityPeriod;

    @Size(max = 4,message = "Pin code is not valid")
    private String pinCode;

    @Size(max = 3,message = "CVV code is not valid")
    private Integer cvvCode;

    private String firstName;

    private String lastName;

    private CardType cardType;

    private Integer bankId;
}
