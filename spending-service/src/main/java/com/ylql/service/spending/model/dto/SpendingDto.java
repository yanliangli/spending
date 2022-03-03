package com.ylql.service.spending.model.dto;

import com.ylql.service.spending.model.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SpendingDto {
    private String title;

    List<Transaction> transactions;
}
