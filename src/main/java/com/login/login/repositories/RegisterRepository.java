package com.login.login.repositories;

import com.login.login.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register,Integer> {
    boolean existsByNikIgnoreCase(String nik);
    boolean existsByEmailIgnoreCase(String email);
    Optional<Register> findByEmailIgnoreCase(String email);

    Optional<Register> findByEmail(String email);
}
