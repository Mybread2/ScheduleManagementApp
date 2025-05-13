package com.example.schedulemanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {
    private String todo;
    private Long authorId;
    private String password;
}
