package com.example.schedulemanagementapp.dto;

import com.example.schedulemanagementapp.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String author;
    private String password;
    private String created_at;
    private String modified_at;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = schedule.getAuthor();
        this.password = schedule.getPassword();
        this.created_at = schedule.getCreated_at();
        this.modified_at = schedule.getModified_at();
    }
}
