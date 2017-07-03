package com.aksigorta.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aksigorta.account.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("Select u From User u Where u.username=?1")
	User findByUsername(String username);
}
