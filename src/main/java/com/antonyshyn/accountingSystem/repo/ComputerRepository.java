package com.antonyshyn.accountingSystem.repo;

import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Optional<Computer> findComputerById(Long id);

    Optional<Long> deleteComputerById(Long id);

    List<Computer> findComputersByStudyRoom(StudyRoom studyRoom);

    Optional<Set<Computer>> findComputersByIdIsIn(Collection<Long> id);
}
