package com.examly.springapp.controller;

import java.util.List;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Role;
import com.examly.springapp.service.RoleService;

@RestController
@RequestMapping(path = "/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    
    @PostMapping()
    public ResponseEntity<Role> postRole(@RequestBody Role roles){
        Role newRole = roleService.AddRole(roles);
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create("/api/roles/" + newRole.getId()))
                .body(newRole);
    }

    @GetMapping("/page/{pageNo}/{pageSize}")
    public Page<Role> getRole(@PathVariable int pageNo,@PathVariable int pageSize){
        return roleService.paginationandSorting(pageNo,pageSize);
    }

    @GetMapping()
    public ResponseEntity<List<Role>> getRole(){
        return ResponseEntity.ok(roleService.GetAllRoles());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable long roleId){
        try {
            Role role = roleService.GetRoleById(roleId);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    

    @PutMapping(path = "/{roleId}")
    public ResponseEntity<Role> putRole(@PathVariable long roleId,@RequestBody Role role){
        Role nRole = roleService.UpdateRole(roleId,role);
        if(nRole!=null){
            return ResponseEntity.status(HttpStatus.OK).body(nRole);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping(path = "/{roleId}")
    public ResponseEntity<Role> deletRole(@PathVariable int roleId){
        if(roleService.DeleteRole(roleId)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
