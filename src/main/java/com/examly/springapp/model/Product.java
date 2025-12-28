package com.examly.springapp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    
    private String productName;
    private long price;
    private Integer stockQuantity;
    
    @OneToMany(mappedBy = "product")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<OrderItem> orderItems;
    
    public Product() {}
    
    public Product(String productName, Long price, Integer stockQuantity) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    public Long getId() { 
        return productId; 
    }
    public void setId(Long id) { 
        this.productId = id; 
    }
    
    public Long getProductId() { 
        return productId; 
    }
    public void setProductId(Long productId) { 
        this.productId = productId; 
    }
    
    public String getProductName() { 
        return productName; 
    }
    public void setProductName(String productName) { 
        this.productName = productName; 
    }
    
    public Long getPrice() { 
        return price; 
    }
    public void setPrice(Long price) { 
        this.price = price; 
    }
    
    public Integer getStockQuantity() { 
        return stockQuantity; 
    }
    public void setStockQuantity(Integer stockQuantity) { 
        this.stockQuantity = stockQuantity; 
    }
    
    public List<OrderItem> getOrderItems() { 
        return orderItems; 
    }
    public void setOrderItems(List<OrderItem> orderItems) { 
        this.orderItems = orderItems; 
    }
}