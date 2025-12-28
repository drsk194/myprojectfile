package com.examly.springapp.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class UserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-userRoleMapping")
    private Customer user;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    @JsonBackReference("role-userRoleMapping")
    private Role role;

    public UserRoleMapping() {}

    public Long getId() { return id; }
    public Customer getUser() { return user; }
    public Role getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setUser(Customer user) { this.user = user; }
    public void setRole(Role role) { this.role = role; }
    
    @JsonProperty("customerId")
    public void setCustomerId(Long customerId) {
        if (customerId != null) {
            this.user = new Customer();
            this.user.setId(customerId);
        }
    }
    
    @JsonProperty("roleId")
    public void setRoleId(Long roleId) {
        if (roleId != null) {
            this.role = new Role();
            this.role.setId(roleId);
        }
    }
}
