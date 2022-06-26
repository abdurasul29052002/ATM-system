package com.example.appspringatmsystem.repository;

import com.example.appspringatmsystem.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Optional<Card> findByNumber(Long number);
}
