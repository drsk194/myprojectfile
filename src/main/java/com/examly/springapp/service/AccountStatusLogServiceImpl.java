package com.examly.springapp.service;

import com.examly.springapp.model.AccountStatusLog;
import com.examly.springapp.repository.AccountStatusLogRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountStatusLogServiceImpl implements AccountStatusLogService {
	private final AccountStatusLogRepo repo;
	public AccountStatusLogServiceImpl(AccountStatusLogRepo repo) { this.repo = repo; }

	@Override public AccountStatusLog create(AccountStatusLog log) { return repo.save(log); }
	@Override public List<AccountStatusLog> findAll() { return repo.findAll(); }
	@Override public Optional<AccountStatusLog> findById(Long id) { return repo.findById(id); }
	@Override public AccountStatusLog update(Long id, AccountStatusLog log) {
		AccountStatusLog existing = repo.findById(id).orElseThrow();
		existing.setOldStatus(log.getOldStatus());
		existing.setNewStatus(log.getNewStatus());
		existing.setChangedAt(log.getChangedAt());
		existing.setChangedBy(log.getChangedBy());
		return repo.save(existing);
	}
	@Override public void delete(Long id) { repo.deleteById(id); }
}
