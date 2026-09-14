package com.project.ms_customer.controller;

import com.project.ms_customer.dto.*;
import com.project.ms_customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CustomerCreateRequest req) {
        return customerService.create(req);
    }

    @PostMapping("/{customerId}/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse addCard(@PathVariable Long customerId, @Valid @RequestBody CardCreateRequest req) {
        return customerService.addCard(customerId, req);
    }

    @GetMapping("/{customerId}/cards/default")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse getDefaultCard(@PathVariable Long customerId) {
        return customerService.getDefaultCard(customerId);
    }

    // internal API for payment
    @PostMapping("/internal/{customerId}/charge")
    @ResponseStatus(HttpStatus.OK)
    public ChargeResponse charge(@PathVariable Long customerId, @Valid @RequestBody ChargeRequest req) {
        return customerService.charge(customerId, req);
    }
}