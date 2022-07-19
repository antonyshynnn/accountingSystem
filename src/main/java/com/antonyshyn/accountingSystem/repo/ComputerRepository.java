package com.antonyshyn.accountingSystem.repo;

import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.entity.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Optional<Computer> findComputerById(Long id);

    Optional<Long> deleteComputerById(Long id);

    List<Computer> findComputersByRoomId(Long id);

}
