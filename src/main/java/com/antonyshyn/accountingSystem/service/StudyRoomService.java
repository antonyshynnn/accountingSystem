package com.antonyshyn.accountingSystem.service;

import com.antonyshyn.accountingSystem.dto.StudyRoomDTO;
import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.entity.UserEntity;
import com.antonyshyn.accountingSystem.exception.ComputerNotFoundException;
import com.antonyshyn.accountingSystem.exception.StudyRoomNotFoundException;
import com.antonyshyn.accountingSystem.repo.ComputerRepository;
import com.antonyshyn.accountingSystem.repo.StudyRoomRepository;
import com.antonyshyn.accountingSystem.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class StudyRoomService {
    StudyRoomRepository studyRoomRepository;
    ComputerRepository computerRepository;
    UserRepository userRepository;

    public StudyRoom addStudyRoom(StudyRoomDTO studyRoomDTO) {
        Set<UserEntity> users = userRepository.findUserEntitiesByIdIsIn(studyRoomDTO.getTeacherIds()).orElseThrow(() -> new StudyRoomNotFoundException("Cannot find study room with number " + studyRoomDTO.getRoomNumber()));
        Set<Computer> computers = computerRepository.findComputersByIdIsIn(studyRoomDTO.getComputerIds()).orElseThrow();

        StudyRoom studyRoom = new StudyRoom(studyRoomDTO.getRoomNumber(), users, computers);
        return studyRoomRepository.save(studyRoom);
    }

    public void deleteRoom(Long roomId) {
        studyRoomRepository.deleteById(roomId);
    }

}
