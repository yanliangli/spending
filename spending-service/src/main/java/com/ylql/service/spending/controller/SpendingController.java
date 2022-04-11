package com.ylql.service.spending.controller;

import com.ylql.service.spending.model.Spending;
import com.ylql.service.spending.model.dto.SpendingDto;
import com.ylql.service.spending.service.SpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/app/spending/v1/spending")
public class SpendingController {
    @Autowired
    private SpendingService spendingService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SpendingDto>> getAllSpending() {
        return new ResponseEntity<>(spendingService.getAllSpendingReport(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Spending> getSpendingById (@PathVariable UUID id) {
        return new ResponseEntity<>(spendingService.getSpendingById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Spending> createSpending(@RequestBody SpendingDto spendingDto) {
        return new ResponseEntity<>(spendingService.createSpending(spendingDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/{spendingId}", method = RequestMethod.POST)
    public ResponseEntity<Spending> updateSpending(@PathVariable UUID spendingId, @RequestBody SpendingDto spendingDto) {
        return new ResponseEntity<>(spendingService.updateSpending(spendingId, spendingDto), HttpStatus.OK);
    }
}
