package com.ylql.service.spending.repository;

import com.ylql.service.spending.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {
    Spending findById(UUID id);
}
