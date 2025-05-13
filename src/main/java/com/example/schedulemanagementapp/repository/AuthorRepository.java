package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.entity.Author;

import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    Optional<Author> findById(Long id);
}
