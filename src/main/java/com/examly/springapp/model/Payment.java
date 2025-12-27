package com.examly.springapp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    private BigDecimal paymentAmount;
    
    public enum PaymentMethod {
        CREDIT_CARD, PAYPAL, CASH, DEBIT_CARD
    }
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    public Payment() {}
    
    public Payment(Order order, BigDecimal paymentAmount, PaymentMethod paymentMethod) {
        this.order = order;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
    }
    
    public Long getPaymentId() { 
        return paymentId; 
    }
    public void setPaymentId(Long paymentId) { 
        this.paymentId = paymentId; 
    }
    
    public Order getOrder() { 
        return order; 
    }
    public void setOrder(Order order) { 
        this.order = order; 
    }
    
    public BigDecimal getPaymentAmount() { 
        return paymentAmount; 
    }
    public void setPaymentAmount(BigDecimal paymentAmount) { 
        this.paymentAmount = paymentAmount; 
    }
    
    public PaymentMethod getPaymentMethod() { 
        return paymentMethod; 
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) { 
        this.paymentMethod = paymentMethod; 
    }
}