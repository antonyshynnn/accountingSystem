package com.antonyshyn.accountingSystem.repo;

import com.antonyshyn.accountingSystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity>findUserEntitiesByUsername(String username);
    Optional<Set<UserEntity>>findUserEntitiesByIdIsIn(Collection<Long> id);

    boolean existsByUsername(String username);
}
