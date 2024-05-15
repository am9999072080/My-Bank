package com.am.MyBank.repository;

import com.am.MyBank.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepository extends CrudRepository<Card, Long> {
}
