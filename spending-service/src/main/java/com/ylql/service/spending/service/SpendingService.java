package com.ylql.service.spending.service;

import com.ylql.service.spending.model.Spending;
import com.ylql.service.spending.model.dto.SpendingDto;
import com.ylql.service.spending.repository.SpendingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SpendingService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpendingRepository spendingRepository;

    public List<SpendingDto> getAllSpendingReport() {
        List<Spending> spendingList = spendingRepository.findAll();
        return spendingList.stream().map(this::transformSpendingDto).collect(Collectors.toList());
    }

    private SpendingDto transformSpendingDto (Spending spending) {
        SpendingDto spendingDto = new SpendingDto();
        spendingDto.setTitle(spending.getTitle());
        spendingDto.setId(spending.getId().toString());
        spendingDto.setLastModifiedDate(spending.getLastModifiedDate());
        spendingDto.setCreateDate(spending.getCreateDate());
        return spendingDto;
    }
    public Spending createSpending(SpendingDto spendingDto) {
        Spending spending = new Spending();
        spending.setTitle(spendingDto.getTitle());
        return spendingRepository.save(spending);
    }

    public Spending updateSpending(UUID spendingId, SpendingDto spendingDto) {
        Spending spending = spendingRepository.findById(spendingId);
        if (spending == null) {
            log.error("Spending does not exist.");
            throw new RuntimeException("Spending not found");
        }
        spending.setTitle(spendingDto.getTitle());
        return spendingRepository.save(spending);
    }

    public Spending getSpendingById(UUID id) {
        return spendingRepository.findById(id);
    }
}
