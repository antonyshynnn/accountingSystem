package com.antonyshyn.accountingSystem.dto;

import com.antonyshyn.accountingSystem.entity.enums.Drive;
import com.antonyshyn.accountingSystem.entity.enums.PCType;
import com.antonyshyn.accountingSystem.entity.enums.Status;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerDTO implements Serializable {
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
    private Long studyRoomId;
    private String reportMessage;
    private Status status;
}
