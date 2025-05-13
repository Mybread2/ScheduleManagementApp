package com.example.schedulemanagementapp.entity;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Schedule {
    private Long id;
    @NonNull
    private String todo;
    @NonNull
    private Author author;
    @NonNull
    private String password;
    @NonNull
    private String created_at;
    @NonNull
    private String modified_at;

}
