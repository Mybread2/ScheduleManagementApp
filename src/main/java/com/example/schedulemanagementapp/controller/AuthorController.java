package com.example.schedulemanagementapp.controller;

import com.example.schedulemanagementapp.dto.AuthorRequestDto;
import com.example.schedulemanagementapp.entity.Author;
import com.example.schedulemanagementapp.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRequestDto dto){
        Author savedAuthorId = authorService.save(dto);
        return ResponseEntity.ok(savedAuthorId);
    }
}
