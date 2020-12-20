package com.uber.uberapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="paymentReceipt")
public class PaymentReceipt extends Auditable{
    private Double Amount;

    @ManyToOne
    private PaymentGateway paymentGateway;
    private String details;

}
