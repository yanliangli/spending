package com.ylql.service.spending.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SpendingDto {
    private String id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastModifiedDate;
}
