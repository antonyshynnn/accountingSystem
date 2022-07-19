package com.antonyshyn.accountingSystem.resource;

import com.antonyshyn.accountingSystem.entity.UserEntity;
import com.antonyshyn.accountingSystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        UserEntity newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
