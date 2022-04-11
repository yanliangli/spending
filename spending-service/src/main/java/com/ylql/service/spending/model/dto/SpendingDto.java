package com.ylql.service.spending.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SpendingDto {
    private String id;
    private String title;
    private Date createDate;
    private Date lastModifiedDate;
}
