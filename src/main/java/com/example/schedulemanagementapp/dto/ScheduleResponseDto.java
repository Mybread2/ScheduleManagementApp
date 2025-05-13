package com.example.schedulemanagementapp.dto;

import com.example.schedulemanagementapp.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String todo;
    private final String author;
    private final String password;
    private final String created_at;
    private final String modified_at;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.password = schedule.getPassword();
        this.created_at = schedule.getCreated_at();
        this.modified_at = schedule.getModified_at();
    }
}
