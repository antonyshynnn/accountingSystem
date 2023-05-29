package com.antonyshyn.accountingSystem.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomDTO {
    private Long roomNumber;

    private Set<Long> teacherIds;

    private Set<Long> computerIds;

}
