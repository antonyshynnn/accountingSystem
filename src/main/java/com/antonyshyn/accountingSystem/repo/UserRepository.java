package com.antonyshyn.accountingSystem.repo;

import com.antonyshyn.accountingSystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity>findUserEntitiesByUsername(String username);

    boolean existsByUsername(String username);
}
