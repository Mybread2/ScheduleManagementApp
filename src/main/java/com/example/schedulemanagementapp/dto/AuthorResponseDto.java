package com.example.schedulemanagementapp.dto;

import com.example.schedulemanagementapp.entity.Author;
import lombok.Getter;

@Getter
public class AuthorResponseDto {
    private final Long id;
    private final String name;
    private final String email;
    private final String createdAt;
    private final String modifiedAt;

    public AuthorResponseDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
        this.createdAt = author.getCreated_at();
        this.modifiedAt = author.getModified_at();
    }
}

