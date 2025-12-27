package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.examly.springapp.model.Role;

public interface RoleService {

    public Role AddRole(Role role);
    public List<Role> GetAllRoles();
    public Role GetRoleById(long id);
    public Role UpdateRole(long id,Role role);
    public boolean DeleteRole(long id);
    Page<Role> paginationandSorting(int pageNo,int pageSize);
}