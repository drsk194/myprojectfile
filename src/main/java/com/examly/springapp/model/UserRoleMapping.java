package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class UserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer user;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRoleMapping() {}

    public Long getId() { return id; }
    public Customer getUser() { return user; }
    public Role getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setUser(Customer user) { this.user = user; }
    public void setRole(Role role) { this.role = role; }
}
