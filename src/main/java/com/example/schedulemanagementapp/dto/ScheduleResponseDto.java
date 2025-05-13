package com.example.schedulemanagementapp.dto;

import com.example.schedulemanagementapp.entity.Author;
import com.example.schedulemanagementapp.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String todo;
    private final Long authorId;
    private final String authorName;
    private final String authorEmail;
    private final String password;
    private final String created_at;
    private final String modified_at;
    private final String authorCreatedAt;
    private final String authorModifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.todo = schedule.getTodo();
        Author author = schedule.getAuthor();
        this.authorId = author.getId();
        this.authorName = author.getName();
        this.authorEmail = author.getEmail();
        this.authorCreatedAt = author.getCreated_at();
        this.authorModifiedAt = author.getModified_at();
        this.password = schedule.getPassword();
        this.created_at = schedule.getCreated_at();
        this.modified_at = schedule.getModified_at();
    }
}