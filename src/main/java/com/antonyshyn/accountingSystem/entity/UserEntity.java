package com.antonyshyn.accountingSystem.entity;

import com.antonyshyn.accountingSystem.entity.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_entity")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String username;
    private String password;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    @JsonManagedReference
    private StudyRoom studyRoom;

    @Enumerated(EnumType.STRING)
    private UserRoles userRole;

    public UserEntity(String username, String password, String email, UserRoles userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    public UserEntity(String username, String email, UserRoles userRole) {
        this.username = username;
        this.email = email;
        this.userRole = userRole;
    }
}
