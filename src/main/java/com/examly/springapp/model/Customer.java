package com.examly.springapp.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    
    @Transient
    private String username;
    
    @Transient
    private String passwordHash;
    
    public enum Status {
        ACTIVE, INACTIVE
    }
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;
    
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Profile profile;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;
    
    public Customer() {}
    
    public Customer(String firstName, String lastName, String email, String phone, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }
    
    @JsonProperty("id")
    public Long getId() {
        return customerId;
    }
    
    public void setId(Long id) {
        this.customerId = id;
    }
    
    public Long getCustomerId() { 
        return customerId; 
    }
    public void setCustomerId(Long customerId) { 
        this.customerId = customerId; 
    }
    
    public String getFirstName() { 
        return firstName; 
    }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    
    public String getLastName() { 
        return lastName; 
    }
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) {
         this.email = email; 
        }
    
    public String getPhone() { 
        return phone; 
    }
    public void setPhone(String phone) { 
        this.phone = phone; 
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public Status getStatus() { 
        return status; 
    }
    public void setStatus(Status status) { 
        this.status = status; 
    }
    
    public Profile getProfile() { 
        return profile; 
    }
    public void setProfile(Profile profile) { 
        this.profile = profile; 
    }
    
    public List<Order> getOrders() { 
        return orders; 
    }
    public void setOrders(List<Order> orders) { 
        this.orders = orders; 
    }
    
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}