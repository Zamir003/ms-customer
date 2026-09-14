package com.project.ms_customer.dto;

import com.project.ms_customer.model.RecordStatus;

import java.math.BigDecimal;

public record CardResponse(
        Long id,
        Long customerId,
        String panLast4,
        BigDecimal balance,
        String currency,
        RecordStatus status
) {}