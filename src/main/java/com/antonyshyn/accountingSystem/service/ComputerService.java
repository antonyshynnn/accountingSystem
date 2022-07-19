package com.antonyshyn.accountingSystem.service;

import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.exception.ComputerNotFoundException;
import com.antonyshyn.accountingSystem.repo.ComputerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ComputerService {
    ComputerRepository computerRepository;



    public Computer findComputerById(Long id) {
        return computerRepository.findComputerById(id)
                .orElseThrow(() -> new ComputerNotFoundException("Computer was not found with id: " + id));
    }

    public List<Computer> findAllComputers() {
        return computerRepository.findAll();
    }

    public List<Computer> findAllComputersByRoom(Long id) {
        return computerRepository.findComputersByRoomId(id);
    }

    public Computer addComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public Computer updateComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public Long deleteComputer(Long id) {
        return computerRepository.deleteComputerById(id).orElseThrow(() -> new ComputerNotFoundException("Cannot delete computer with id: "  + id
        + ", because there is no such id"));
    }

}
