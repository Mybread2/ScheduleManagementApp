package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.entity.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Author save(Author author) {
        String sql = "INSERT INTO author (name, email, created_at, modified_at) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getEmail());
            ps.setString(3, author.getCreated_at());
            ps.setString(4, author.getModified_at());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            Author savedAuthor = new Author(
                    author.getName(),
                    author.getEmail(),
                    author.getCreated_at(),
                    author.getModified_at()
            );
            savedAuthor.setId(key.longValue());
            return savedAuthor;
        }
        return author;
    }


    @Override
    public Optional<Author> findById(Long id) {
        String sql = "SELECT * FROM author WHERE id = ?";
        try {
            Author author = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Author a = new Author(
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("created_at"),
                                rs.getString("modified_at")
                        );
                        a.setId(rs.getLong("id"));
                        return a;
                    }
            );
            return Optional.ofNullable(author);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
