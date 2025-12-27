package com.examly.springapp.service;

import com.examly.springapp.model.UserAccount;
import java.util.List;
import java.util.Optional;

public interface UserAccountService {
	UserAccount create(UserAccount userAccount);
	List<UserAccount> findAll();
	Optional<UserAccount> findById(Long id);
	UserAccount update(Long id, UserAccount userAccount);
	void delete(Long id);
}