package com.antonyshyn.accountingSystem.resource;

import com.antonyshyn.accountingSystem.dto.StudyRoomDTO;
import com.antonyshyn.accountingSystem.entity.Computer;
import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.entity.UserEntity;
import com.antonyshyn.accountingSystem.service.ComputerService;
import com.antonyshyn.accountingSystem.service.StudyRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final StudyRoomService studyRoomService;

    public RoomController(StudyRoomService studyRoomService) {
        this.studyRoomService = studyRoomService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<StudyRoom> addRoom(@RequestBody StudyRoomDTO studyRoomDTO) {
        StudyRoom newRoom = studyRoomService.addStudyRoom(studyRoomDTO);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> deleteStudyRoom(@PathVariable("id") Long id) {
        studyRoomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
