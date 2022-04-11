package com.ylql.service.spending.service;

import com.ylql.service.spending.model.Spending;
import com.ylql.service.spending.model.Transaction;
import com.ylql.service.spending.model.dto.TransactionDto;
import com.ylql.service.spending.repository.SpendingRepository;
import com.ylql.service.spending.repository.TransactionRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SpendingRepository spendingRepository;

    public Transaction addTransaction(UUID spendingId, TransactionDto transactionDto) throws NotFoundException {
        Spending spending = spendingRepository.findById(spendingId);
        if (spending == null) {
            log.error("Spending record does not exist");
            throw new NotFoundException("Spending not found");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.getAmount());
        newTransaction.setCategory(transactionDto.getCategory());
        newTransaction.setDate(transactionDto.getDate());
        newTransaction.setDescription(transactionDto.getDescription());
        newTransaction.setPersonName(transactionDto.getPersonName());
        newTransaction.setSpending(spending);
        return transactionRepository.save(newTransaction);
    }

    public Transaction updateTransaction(UUID transactionId, TransactionDto transactionDto) throws NotFoundException {
        Transaction transaction = transactionRepository.findById(transactionId);
        if (transaction == null) {
            log.error("Transaction record does not exist");
            throw new NotFoundException("Transaction not found");
        }
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setPersonName(transactionDto.getPersonName());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(UUID spendingId) {
        return transactionRepository.findAllBySpendingId(spendingId);
    }
}
