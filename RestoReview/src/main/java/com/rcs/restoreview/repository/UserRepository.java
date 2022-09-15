package com.rcs.restoreview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcs.restoreview.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByDisplayName(String displayName);
}
