package com.examly.springapp.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    
    private String roleName;
    
    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private List<Customer> customers;
    
    @OneToMany(mappedBy = "role")
    @JsonManagedReference("role-userRoleMapping")
    private List<UserRoleMapping> userRoleMappings;
    
    public Role() {}
    public Role(String roleName) { 
        this.roleName = roleName; 
    }

    public Long getRoleId() { 
        return roleId; 
    }
    public void setRoleId(Long roleId) { 
        this.roleId = roleId; 
    }
    
    public Long getId() { 
        return roleId; 
    }
    public void setId(Long id) { 
        this.roleId = id; 
    }
    
    public String getRoleName() { 
        return roleName; 
    }
    public void setRoleName(String roleName) { 
        this.roleName = roleName; 
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    
    public List<UserRoleMapping> getUserRoleMappings() {
        return userRoleMappings;
    }
    public void setUserRoleMappings(List<UserRoleMapping> userRoleMappings) {
        this.userRoleMappings = userRoleMappings;
    }
}
