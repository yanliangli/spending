package com.ylql.service.spending.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class TransactionDto {
    private UUID id;

    private String description;

    private double amount;

    private String Category;

    private Date date;

    private String personName;
}
