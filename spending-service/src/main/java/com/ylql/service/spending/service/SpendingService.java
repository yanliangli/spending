package com.ylql.service.spending.service;

import com.ylql.service.spending.model.Spending;
import com.ylql.service.spending.model.dto.SpendingDto;
import com.ylql.service.spending.repository.SpendingRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpendingService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpendingRepository spendingRepository;

    public List<Spending> getAllSpendingReport() {
        return spendingRepository.findAll();
    }

    public Spending createSpending(SpendingDto spendingDto) {
        Spending spending = new Spending();
        spending.setTitle(spendingDto.getTitle());
        return spendingRepository.save(spending);
    }

    public Spending updateSpending(String spendingId, SpendingDto spendingDto) {
        Optional<Spending> optionalSpending = spendingRepository.findById(Long.valueOf(spendingId));
        if (!optionalSpending.isPresent()) {
            log.error("Spending does not exist.");
            throw new RuntimeException("Spending not found");
        }
        Spending spending = optionalSpending.get();
        spending.setTitle(spendingDto.getTitle());
        spending.setTransactions(spendingDto.getTransactions());
        return spendingRepository.save(spending);
    }
}
