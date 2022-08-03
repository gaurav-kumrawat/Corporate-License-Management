package com.psl.alp.UserManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.alp.UserManagement.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
