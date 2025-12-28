package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    
    private LocalDate orderDate;
    
    public enum OrderStatus {
        PENDING, COMPLETED, CANCELED
    }
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Payment payment;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<OrderItem> orderItems;
    
    public Order() {}
    
    public Order(Customer customer, LocalDate orderDate, OrderStatus orderStatus) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
    
    public Long getId() { 
        return orderId; 
    }
    public void setId(Long id) { 
        this.orderId = id; 
    }
    
    public Long getOrderId() { 
        return orderId; 
    }
    public void setOrderId(Long orderId) {
         this.orderId = orderId; 
        }
    
    public Customer getCustomer() { 
        return customer; 
    }
    public void setCustomer(Customer customer) { 
        this.customer = customer; 
    }
    
    public LocalDate getOrderDate() { 
        return orderDate; 
    }
    public void setOrderDate(LocalDate orderDate) { 
        this.orderDate = orderDate; 
    }
    
    public OrderStatus getOrderStatus() { 
        return orderStatus; 
    }
    public void setOrderStatus(OrderStatus orderStatus) { 
        this.orderStatus = orderStatus; 
    }
    
    public Payment getPayment() { 
        return payment; 
    }
    public void setPayment(Payment payment) { 
        this.payment = payment; 
    }
    
    public List<OrderItem> getOrderItems() { 
        return orderItems; 
    }
    public void setOrderItems(List<OrderItem> orderItems) { 
        this.orderItems = orderItems; 
    }
}