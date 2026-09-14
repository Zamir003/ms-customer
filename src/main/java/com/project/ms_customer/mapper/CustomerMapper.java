package com.project.ms_customer.mapper;

import com.project.ms_customer.dto.CustomerCreateRequest;
import com.project.ms_customer.dto.CustomerResponse;
import com.project.ms_customer.model.Customer;

public class CustomerMapper {
    public static Customer toEntity(CustomerCreateRequest req) {
        Customer c = new Customer();
        c.setFullName(req.fullName());
        c.setEmail(req.email());
        return c;
    }

    public static CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(c.getId(), c.getFullName(), c.getEmail(), c.getStatus());
    }
}