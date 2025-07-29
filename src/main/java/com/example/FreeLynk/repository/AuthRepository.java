package com.example.FreeLynk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FreeLynk.model.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByEmail(String email);
}
