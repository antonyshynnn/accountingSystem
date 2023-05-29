package com.antonyshyn.accountingSystem.repo;

import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudyRoomRepository extends CrudRepository<StudyRoom, Long> {
    Optional<StudyRoom>findStudyRoomByRoomId(Long id);
    Optional<StudyRoom>findStudyRoomByRoomNumber(Long roomNumber);


}
