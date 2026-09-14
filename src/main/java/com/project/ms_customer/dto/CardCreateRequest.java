package com.project.ms_customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CardCreateRequest(
        @NotBlank String panLast4,
        @NotNull BigDecimal initialBalance,
        @NotBlank String currency
) {}