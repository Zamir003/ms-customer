package com.project.ms_customer.dto;

import com.project.ms_customer.model.RecordStatus;

public record CustomerResponse(
        Long id,
        String fullName,
        String email,
        RecordStatus status
) {}