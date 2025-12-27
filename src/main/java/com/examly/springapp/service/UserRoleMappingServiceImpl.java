package com.examly.springapp.service;

import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.repository.UserRoleMappingRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleMappingServiceImpl implements UserRoleMappingService {
	private final UserRoleMappingRepo repo;
	public UserRoleMappingServiceImpl(UserRoleMappingRepo repo) { this.repo = repo; }

	@Override public UserRoleMapping create(UserRoleMapping mapping) { return repo.save(mapping); }
	@Override public List<UserRoleMapping> findAll() { return repo.findAll(); }
	@Override public Optional<UserRoleMapping> findById(Long id) { return repo.findById(id); }
	@Override public UserRoleMapping update(Long id, UserRoleMapping mapping) {
		UserRoleMapping existing = repo.findById(id).orElseThrow();
		existing.setUser(mapping.getUser());
		existing.setRole(mapping.getRole());
		return repo.save(existing);
	}
	@Override public List<UserRoleMapping> findByUserId(Long userId) { return repo.findByUser_CustomerId(userId); }
	@Override public List<UserRoleMapping> findByUserIdAndRoleId(Long userId, Long roleId) { return repo.findByUser_CustomerIdAndRole_RoleId(userId, roleId); }
	@Override public void delete(Long id) { repo.deleteById(id); }
}