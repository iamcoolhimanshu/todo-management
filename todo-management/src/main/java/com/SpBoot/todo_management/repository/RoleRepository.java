package com.SpBoot.todo_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SpBoot.todo_management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
