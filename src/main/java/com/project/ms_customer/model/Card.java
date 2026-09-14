package com.project.ms_customer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cards")
@SQLDelete(sql = "UPDATE cards SET status='DELETED' WHERE id=?")
@SQLRestriction("status <> 'DELETED'")
public class Card extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer customer;

    @Column(nullable = false)
    private String panLast4;

    @Column(nullable = false)
    private BigDecimal balance = ZERO;

    @Column(nullable = false)
    private String currency = "AZN";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecordStatus status = RecordStatus.ACTIVE;
}