package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    
    private String roleName;
    
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
}
