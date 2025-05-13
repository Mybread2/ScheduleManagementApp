package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.entity.Author;
import com.example.schedulemanagementapp.entity.Schedule;
import com.example.schedulemanagementapp.exception.ScheduleNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (todo, author_id, password, created_at, modified_at) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, schedule.getTodo());
            ps.setLong(2, schedule.getAuthor().getId());
            ps.setString(3, schedule.getPassword());
            ps.setString(4, schedule.getCreated_at());
            ps.setString(5, schedule.getModified_at());
            return ps;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            schedule.setId(generatedId.longValue());
        }

        return schedule;
    }

    @Override
    public List<Schedule> findSchedules(String modifiedDate, String authorName) {
        StringBuilder sql = new StringBuilder(
                "SELECT s.*, a.name as author_name, a.email as author_email, a.created_at as author_created_at, a.modified_at as author_modified_at " +
                        "FROM schedule s JOIN author a ON s.author_id = a.id WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (modifiedDate != null && !modifiedDate.isEmpty()) {
            sql.append(" AND LEFT(s.modified_at, 10) = ?");
            params.add(modifiedDate);
        }

        if (authorName != null && !authorName.isEmpty()) {
            sql.append(" AND a.name = ?");
            params.add(authorName);
        }

        sql.append(" ORDER BY s.modified_at DESC");

        return jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                (rs, rowNum) -> {
                    Author author = new Author(
                            rs.getLong("author_id"),
                            rs.getString("author_name"),
                            rs.getString("author_email"),
                            rs.getString("author_created_at"),
                            rs.getString("author_modified_at")
                    );
                    return new Schedule(
                            rs.getLong("id"),
                            rs.getString("todo"),
                            author,
                            rs.getString("password"),
                            rs.getString("created_at"),
                            rs.getString("modified_at")
                    );
                }
        );
    }


    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT s.*, a.name as author_name, a.email as author_email, a.created_at as author_created_at, a.modified_at as author_modified_at FROM schedule s JOIN author a ON s.author_id = a.id WHERE s.id = ?";
        try {
            Schedule schedule = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Author author = new Author(
                                rs.getLong("author_id"),
                                rs.getString("author_name"),
                                rs.getString("author_email"),
                                rs.getString("author_created_at"),
                                rs.getString("author_modified_at")
                        );
                        return new Schedule(
                                rs.getLong("id"),
                                rs.getString("todo"),
                                author,
                                rs.getString("password"),
                                rs.getString("created_at"),
                                rs.getString("modified_at")
                        );
                    }
            );
            return Optional.of(schedule);
        } catch (EmptyResultDataAccessException e) {
            throw new ScheduleNotFoundException("해당 ID의 일정이 존재하지 않습니다. ID = " + id);
        }
    }

    @Override
    public int updateSchedule(Long id, String todo, Long authorId, String password, String modified_at) {
        String sql = "UPDATE schedule SET todo = ?, author_id = ?, password = ?, modified_at = ? WHERE id = ? AND password = ?";
        return jdbcTemplate.update(sql, todo, authorId, password, modified_at, id, password);
    }

    @Override
    public int deleteByIdAndPassword(Long id, String password) {
        String sql = "DELETE FROM schedule WHERE id = ? AND password = ?";
        return jdbcTemplate.update(sql, id, password);
    }
}
