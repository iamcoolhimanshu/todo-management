package com.SpBoot.todo_management.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SpBoot.todo_management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsernames(String username);

	Boolean exitsByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

}
