package com.example.schedulemanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {

    private String todo;
    private String author;
    private String password;
}
