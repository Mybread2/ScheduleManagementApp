package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.dto.AuthorRequestDto;
import com.example.schedulemanagementapp.entity.Author;
import com.example.schedulemanagementapp.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    @Override
    @Transactional
    public Author save(AuthorRequestDto dto) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Author author = new Author(null, dto.getName(), dto.getEmail(), now, now);
        return authorRepository.save(author);
    }

}
