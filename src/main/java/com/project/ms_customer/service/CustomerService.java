package com.project.ms_customer.service;

import com.project.ms_customer.dto.*;
import com.project.ms_customer.exception.custom.BadRequestException;
import com.project.ms_customer.exception.custom.NotFoundException;
import com.project.ms_customer.mapper.CustomerMapper;
import com.project.ms_customer.model.Card;
import com.project.ms_customer.model.Customer;
import com.project.ms_customer.repo.CardRepository;
import com.project.ms_customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;

    @Transactional
    public CustomerResponse create(CustomerCreateRequest req) {
        Customer c = CustomerMapper.toEntity(req);
        return CustomerMapper.toResponse(customerRepository.save(c));
    }

    @Transactional
    public CardResponse addCard(Long customerId, CardCreateRequest req) {
        Customer c = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found: " + customerId));

        Card card = new Card();
        card.setCustomer(c);
        card.setPanLast4(req.panLast4());
        card.setBalance(req.initialBalance());
        card.setCurrency(req.currency());

        Card saved = cardRepository.save(card);
        return new CardResponse(saved.getId(), c.getId(), saved.getPanLast4(), saved.getBalance(), saved.getCurrency(), saved.getStatus());
    }

    @Transactional(readOnly = true)
    public CardResponse getDefaultCard(Long customerId) {
        Card card = cardRepository.findFirstByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException("No card found for customer: " + customerId));

        return new CardResponse(card.getId(), card.getCustomer().getId(), card.getPanLast4(), card.getBalance(), card.getCurrency(), card.getStatus());
    }

    @Transactional
    public ChargeResponse charge(Long customerId, ChargeRequest req) {
        Card card = cardRepository.findFirstByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException("No card found for customer: " + customerId));

        if (!card.getCurrency().equalsIgnoreCase(req.currency())) {
            throw new BadRequestException("Currency mismatch. Card=" + card.getCurrency() + ", requested=" + req.currency());
        }

        BigDecimal newBalance = card.getBalance().subtract(req.amount());
        if (newBalance.signum() < 0) {
            return new ChargeResponse(false, "Insufficient balance");
        }

        card.setBalance(newBalance);
        cardRepository.save(card);
        return new ChargeResponse(true, "Charged successfully");
    }
}