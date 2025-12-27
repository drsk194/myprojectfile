package com.examly.springapp.service;

import com.examly.springapp.model.Profile;
import java.util.List;
import java.util.Optional;

public interface ProfileService {
	Profile create(Profile profile);
	List<Profile> findAll();
	Optional<Profile> findById(Long id);
	Profile update(Long id, Profile profile);
	List<Profile> findByName(String firstName);
	List<Profile> searchByNameAndAddress(String firstName, String address);
	void delete(Long id);
}
