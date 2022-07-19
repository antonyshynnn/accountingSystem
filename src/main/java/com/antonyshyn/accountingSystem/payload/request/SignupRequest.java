package com.antonyshyn.accountingSystem.payload.request;

import com.antonyshyn.accountingSystem.entity.StudyRoom;
import com.antonyshyn.accountingSystem.entity.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequest {
//    private String firstName;
//    private String lastName;
    private String username;
    private String password;
    private String email;
    private Long roomId;
    private UserRoles role;
}
