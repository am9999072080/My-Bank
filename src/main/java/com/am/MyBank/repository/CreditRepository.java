package com.am.MyBank.repository;

import com.am.MyBank.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Card, Long> {
}
