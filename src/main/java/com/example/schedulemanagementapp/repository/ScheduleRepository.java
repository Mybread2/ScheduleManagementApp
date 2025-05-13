package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Schedule saveSchedule(Schedule schedule);

    List<Schedule> findSchedules(String modifiedDate, String author);

    Optional<Schedule> findById(Long id);

    int updateSchedule(Long id, String todo, String author, String password, String modified_at);

    int deleteByIdAndPassword(Long id, String password);
}
