package com.ontop.challenge.repository;

import com.ontop.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
