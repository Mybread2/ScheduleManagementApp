package com.example.schedulemanagementapp.repository;

import com.example.schedulemanagementapp.entity.Schedule;

public interface ScheduleRepository {

    Schedule saveSchedule(Schedule schedule);
}
