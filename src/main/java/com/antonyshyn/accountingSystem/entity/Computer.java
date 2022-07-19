package com.antonyshyn.accountingSystem.entity;

import com.antonyshyn.accountingSystem.entity.enums.Drive;
import com.antonyshyn.accountingSystem.entity.enums.PCType;
import com.antonyshyn.accountingSystem.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "computers")
public class Computer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "computer_id",nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private PCType type;
    @Column(nullable = false)
    private String cpu;
    @Column(nullable = false)
    private Integer memory;
    @Column(nullable = false)
    private String motherboard;
    @Column(nullable = false)
    private String coolingSystem;
    @Column(nullable = false)
    private String operatingSystem;
    @Column(nullable = false)
    private Drive drive;
    @Column(nullable = false)
    private Integer driveStorage;
    @Column
    private String imageUrl;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Long roomId;
    @Column
    private String reportMessage;
    @Column
    private Status status;

    public Computer(String name, PCType type, String cpu, Integer memory, String motherboard, String coolingSystem, String operatingSystem, Drive drive, Integer driveStorage, String imageUrl, Integer price, Long roomId, String reportMessage, Status status) {
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
        this.roomId = roomId;
        this.reportMessage = reportMessage;
        this.status = status;
    }
}
