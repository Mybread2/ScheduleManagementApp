package com.example.schedulemanagementapp.dto;

import com.example.schedulemanagementapp.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String todo;
    private final AuthorResponseDto author;
    private final String password;
    private final String createdAt;
    private final String modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        this.author = new AuthorResponseDto(schedule.getAuthor());
        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreated_at();
        this.modifiedAt = schedule.getModified_at();
    }
}
