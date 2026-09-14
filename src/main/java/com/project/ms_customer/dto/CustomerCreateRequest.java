package com.project.ms_customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerCreateRequest(
        @NotBlank String fullName,
        @Email @NotBlank String email
) {}