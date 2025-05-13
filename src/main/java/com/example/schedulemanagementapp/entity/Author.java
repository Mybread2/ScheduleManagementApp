package com.example.schedulemanagementapp.entity;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Author {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String created_at;
    @NonNull
    private String modified_at;
}

