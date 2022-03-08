package com.ylql.service.spending.controller;

import com.ylql.service.spending.model.Transaction;
import com.ylql.service.spending.model.dto.TransactionDto;
import com.ylql.service.spending.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/spending/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(value = "/{spendingId}", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String sendingId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/{spendingId}", method = RequestMethod.PUT)
    public ResponseEntity<List<Transaction>> addTransactions(@PathVariable String spendingId, @RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
