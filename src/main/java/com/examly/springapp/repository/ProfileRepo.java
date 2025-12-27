package com.examly.springapp.repository;

import com.examly.springapp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
	List<Profile> findByCustomer_FirstName(String firstName);
	List<Profile> findByCustomer_FirstNameAndAddress(String firstName, String address);
}
