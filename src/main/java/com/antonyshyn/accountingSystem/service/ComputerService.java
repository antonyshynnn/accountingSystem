package com.antonyshyn.accountingSystem.service;

import com.antonyshyn.accountingSystem.dto.ComputerDTO;
import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.exception.ComputerNotFoundException;
import com.antonyshyn.accountingSystem.exception.StudyRoomNotFoundException;
import com.antonyshyn.accountingSystem.repo.ComputerRepository;
import com.antonyshyn.accountingSystem.repo.StudyRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ComputerService {
    ComputerRepository computerRepository;
    StudyRoomRepository studyRoomRepository;

    public Computer findComputerById(Long id) {
        return computerRepository.findComputerById(id)
                .orElseThrow(() -> new ComputerNotFoundException("Computer was not found with id: " + id));
    }

    public List<Computer> findAllComputers() {
        return computerRepository.findAll();
    }

    public List<Computer> findAllComputersByRoom(Long id) {
        return computerRepository.findComputersByStudyRoom(studyRoomRepository.findStudyRoomByRoomNumber(id).orElseThrow(() -> new StudyRoomNotFoundException("Cannot find study room with id " + id)));
    }

    public Computer addComputer(ComputerDTO computer) {
        Computer newComputer = new Computer(computer.getName(), computer.getType(), computer.getCpu(), computer.getMemory(), computer.getMotherboard(), computer.getCoolingSystem(), computer.getOperatingSystem(), computer.getDrive(), computer.getDriveStorage(), computer.getImageUrl(), computer.getPrice(), computer.getReportMessage(), computer.getStatus());
        newComputer.setStudyRoom(studyRoomRepository.findStudyRoomByRoomNumber(computer.getStudyRoomId()).get());

        return computerRepository.save(newComputer);
    }

    public Computer updateComputer(Computer computer) {
        return computerRepository.save(computer);
    }

    public Long deleteComputer(Long id) {
        return computerRepository.deleteComputerById(id).orElseThrow(() -> new ComputerNotFoundException("Cannot delete computer with id: "  + id
        + ", because there is no such id"));
    }

}
