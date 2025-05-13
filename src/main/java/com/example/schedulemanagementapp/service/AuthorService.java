package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.controller.AuthorController;
import com.example.schedulemanagementapp.dto.AuthorRequestDto;
import com.example.schedulemanagementapp.entity.Author;

public interface AuthorService {
    Author save(AuthorRequestDto dtd);
}
