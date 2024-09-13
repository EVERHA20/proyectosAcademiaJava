package com.academia.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.bookstore.models.Role;

public interface RoleUserRepository extends JpaRepository<Role, Long> {

}
