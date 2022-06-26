package com.example.appspringatmsystem.repository;

import com.example.appspringatmsystem.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMRepository extends JpaRepository<ATM,Integer> {
}
