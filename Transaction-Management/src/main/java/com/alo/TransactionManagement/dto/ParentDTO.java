package com.alo.TransactionManagement.dto;

import lombok.Data;

@Data
public class ParentDTO{
    private Long id;
    private String sender;
    private String receiver;
    private Double totalAmount;
    private Double totalPaidAmount;

}

