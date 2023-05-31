package com.alo.TransactionManagement.util;

public interface ParentWithTotalPaidAmount {
    Long getId();
    String getSender();
    String getReceiver();
    Double getTotalAmount();
    Double getTotalPaidAmount();
}

