package com.antonyshyn.accountingSystem.entity;

import com.antonyshyn.accountingSystem.entity.enums.Drive;
import com.antonyshyn.accountingSystem.entity.enums.PCType;
import com.antonyshyn.accountingSystem.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "computers")
public class Computer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "computer_id",nullable = false, updatable = false)
    private Long id;

    private String name;
    private PCType type;
    private String cpu;
    private Integer memory;
    private String motherboard;
    private String coolingSystem;
    private String operatingSystem;
    private Drive drive;
    private Integer driveStorage;
    private String imageUrl;
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    private StudyRoom studyRoom;

    private String reportMessage;
    private Status status;

    public Computer(String name, PCType type, String cpu, Integer memory, String motherboard, String coolingSystem, String operatingSystem, Drive drive, Integer driveStorage, String imageUrl, Integer price, String reportMessage, Status status) {
        this.name = name;
        this.type = type;
        this.cpu = cpu;
        this.memory = memory;
        this.motherboard = motherboard;
        this.coolingSystem = coolingSystem;
        this.operatingSystem = operatingSystem;
        this.drive = drive;
        this.driveStorage = driveStorage;
        this.imageUrl = imageUrl;
        this.price = price;
        this.reportMessage = reportMessage;
        this.status = status;
    }
}
