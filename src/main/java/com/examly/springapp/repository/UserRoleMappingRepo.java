package com.examly.springapp.repository;

import com.examly.springapp.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMappingRepo extends JpaRepository<UserRoleMapping, Long> {
	List<UserRoleMapping> findByUser_CustomerId(Long userId);
	List<UserRoleMapping> findByUser_CustomerIdAndRole_RoleId(Long userId, Long roleId);
}
