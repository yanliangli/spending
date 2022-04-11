package com.ylql.service.spending.controller;

import com.ylql.service.spending.model.Transaction;
import com.ylql.service.spending.model.dto.TransactionDto;
import com.ylql.service.spending.service.TransactionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/app/spending/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/{spendingId}", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable UUID spendingId) {
        return new ResponseEntity<>(transactionService.getTransactions(spendingId), HttpStatus.OK);
    }
    @RequestMapping(value = "/{spendingId}", method = RequestMethod.POST)
    public ResponseEntity<Transaction> addTransaction(@PathVariable UUID spendingId, @RequestBody TransactionDto transactionDto) throws NotFoundException {
        return new ResponseEntity<>(transactionService.addTransaction(spendingId, transactionDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.PUT)
    public ResponseEntity<Transaction> updateTransaction(@PathVariable UUID transactionId, @RequestBody TransactionDto transactionDto) throws NotFoundException {
        return new ResponseEntity<>(transactionService.updateTransaction(transactionId, transactionDto), HttpStatus.OK);
    }

}
