package com.sevabharat.portal.repository;

import java.util.Optional; // Import the User Entity (our table blueprint)

import org.springframework.data.jpa.repository.JpaRepository;

import com.sevabharat.portal.model.User;

// UserRepository is an interface that inherits methods from JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// Spring Data JPA automatically generates the SQL query: 
	// SELECT * FROM users WHERE username = ?
	Optional<User> findByUsername(String username);

	// Spring Data JPA automatically generates the SQL query:
	// SELECT COUNT(*) FROM users WHERE username = ?
	boolean existsByUsername(String username);
}

