package com.example.appspringatmsystem.service;

import com.example.appspringatmsystem.entity.Bank;
import com.example.appspringatmsystem.entity.Card;
import com.example.appspringatmsystem.payload.CardDto;
import com.example.appspringatmsystem.payload.response.ApiResponse;
import com.example.appspringatmsystem.repository.BankRepository;
import com.example.appspringatmsystem.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;
import java.util.Optional;

@Service
public class CardService implements UserDetailsService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    Base64.Decoder decoder;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public ApiResponse addCard(@RequestBody CardDto cardDto){
        Optional<Bank> optionalBank = bankRepository.findById(cardDto.getBankId());
        if (optionalBank.isEmpty())
            return new ApiResponse("Bank not found",false);

        Card card = new Card();
        card.setCardType(cardDto.getCardType());
        card.setBank(optionalBank.get());
        card.setFirstName(cardDto.getFirstName());
        card.setLastName(cardDto.getLastName());
        card.setNumber(cardDto.getCardNumber());
        card.setCvvCode(cardDto.getCvvCode());
        card.setPinCode(passwordEncoder.encode(cardDto.getPinCode()));
        card.setValidityPeriod(cardDto.getValidityPeriod());
        cardRepository.save(card);
        return new ApiResponse("Card saved",true);
    }

    public ApiResponse withdrawFromCard(String authorization, Double amount) {
        authorization = authorization.substring(6);
        byte[] decode = decoder.decode(authorization);
        String decodedAuthorization = new String(decode);
        String cardNumber = decodedAuthorization.substring(0, 16);
        String pinCode = decodedAuthorization.substring(17);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                cardNumber,
                pinCode
        ));
        return new ApiResponse("Successfully",true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Card> optionalCard = cardRepository.findByNumber(Long.parseLong(username));
        return optionalCard.orElseThrow(()->new BadCredentialsException("Card number is not valid"));
    }
}
