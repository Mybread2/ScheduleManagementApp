package com.example.schedulemanagementapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String todo;
    private Author author;
    private String password;
    private String created_at;
    private String modified_at;

    public Schedule(String todo, Author author, String password, String created_at, String modified_at) {
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

}
