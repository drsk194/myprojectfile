package com.examly.springapp.service;

import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.model.Customer;
import com.examly.springapp.model.Role;
import com.examly.springapp.repository.UserRoleMappingRepo;
import com.examly.springapp.repository.CustomerRepo;
import com.examly.springapp.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleMappingServiceImpl implements UserRoleMappingService {
	private final UserRoleMappingRepo repo;
	private final CustomerRepo customerRepo;
	private final RoleRepo roleRepo;
	
	public UserRoleMappingServiceImpl(UserRoleMappingRepo repo, CustomerRepo customerRepo, RoleRepo roleRepo) { 
		this.repo = repo; 
		this.customerRepo = customerRepo;
		this.roleRepo = roleRepo;
	}

	@Override public UserRoleMapping create(UserRoleMapping mapping) {
		UserRoleMapping newMapping = new UserRoleMapping();
		
		// resolve user
		Customer user = null;
		if (mapping.getUser() != null && mapping.getUser().getId() != null) {
			user = customerRepo.findById(mapping.getUser().getId()).orElse(null);
		}
		
		// resolve role
		Role role = null;
		if (mapping.getRole() != null && mapping.getRole().getId() != null) {
			role = roleRepo.findById(mapping.getRole().getId()).orElse(null);
		}
		
		newMapping.setUser(user);
		newMapping.setRole(role);
		
		return repo.save(newMapping);
	}
	@Override public List<UserRoleMapping> findAll() { return repo.findAll(); }
	@Override public Optional<UserRoleMapping> findById(Long id) { return repo.findById(id); }
	@Override public UserRoleMapping update(Long id, UserRoleMapping mapping) {
		UserRoleMapping existing = repo.findById(id).orElseThrow();
		
		// resolve user
		Customer user = customerRepo.findById(mapping.getUser().getId()).orElse(null);
		// resolve role
		Role role = roleRepo.findById(mapping.getRole().getId()).orElse(null);
		
		existing.setUser(user);
		existing.setRole(role);
		return repo.save(existing);
	}
	@Override public List<UserRoleMapping> findByUserId(Long userId) { return repo.findByUser_Id(userId); }
	@Override public List<UserRoleMapping> findByUserIdAndRoleId(Long userId, Long roleId) { return repo.findByUser_IdAndRole_Id(userId, roleId); }
	@Override public void delete(Long id) { repo.deleteById(id); }
}