package com.antonyshyn.accountingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class StudyRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long roomId;

    private Long roomNumber;

    @OneToMany(mappedBy = "studyRoom", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserEntity> teachers;

    @OneToMany(mappedBy = "studyRoom", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Computer> computers;

    public StudyRoom(Long roomNumber, Set<UserEntity> users, Set<Computer> computers) {
        this.roomNumber = roomNumber;
        this.teachers = users;
        this.computers = computers;
    }
}
