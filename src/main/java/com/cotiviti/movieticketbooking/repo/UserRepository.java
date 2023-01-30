package com.cotiviti.movieticketbooking.repo;

import com.cotiviti.movieticketbooking.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
