package com.ylql.service.spending.repository;

import com.ylql.service.spending.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findById(UUID id);
    List<Transaction> findAllBySpendingId(UUID spendingId);
}
