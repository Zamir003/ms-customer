package com.project.ms_customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ChargeRequest(
        @NotNull BigDecimal amount,
        @NotBlank String currency
) {}