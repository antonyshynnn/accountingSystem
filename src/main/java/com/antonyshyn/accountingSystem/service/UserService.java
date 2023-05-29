package com.antonyshyn.accountingSystem.service;

import com.antonyshyn.accountingSystem.entity.UserEntity;
import com.antonyshyn.accountingSystem.exception.StudyRoomNotFoundException;
import com.antonyshyn.accountingSystem.payload.request.SignupRequest;
import com.antonyshyn.accountingSystem.repo.StudyRoomRepository;
import com.antonyshyn.accountingSystem.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepo;
    private final StudyRoomRepository studyRoomRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> findAllUserEntities() {
        return userRepo.findAll();
    }

    public UserEntity saveUser(SignupRequest signupRequest) {
        UserEntity user = new UserEntity(signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getEmail(), signupRequest.getRole());
        user.setStudyRoom(studyRoomRepository.findStudyRoomByRoomNumber(signupRequest.getRoomId()).orElseThrow(() -> new StudyRoomNotFoundException("Cannot find study room with number " + signupRequest.getRoomId())));
        return userRepo.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepo.save(user);
    }

    public UserEntity findUserEntityByUsername(String username) {
        UserEntity user = userRepo.findUserEntitiesByUsername(username).orElseThrow();
        return user;
    }
}
