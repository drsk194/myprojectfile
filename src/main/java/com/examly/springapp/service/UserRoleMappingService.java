package com.examly.springapp.service;

import com.examly.springapp.model.UserRoleMapping;
import java.util.List;
import java.util.Optional;

public interface UserRoleMappingService {
	UserRoleMapping create(UserRoleMapping mapping);
	List<UserRoleMapping> findAll();
	Optional<UserRoleMapping> findById(Long id);
	UserRoleMapping update(Long id, UserRoleMapping mapping);
	List<UserRoleMapping> findByUserId(Long userId);
	List<UserRoleMapping> findByUserIdAndRoleId(Long userId, Long roleId);
	void delete(Long id);
}
