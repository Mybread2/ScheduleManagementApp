package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.controller.ScheduleController;
import com.example.schedulemanagementapp.dto.ScheduleRequestDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findSchedules(String modifiedDate, String author);

    ScheduleResponseDto findById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto);

    void deleteByIdAndPassword(Long id, String password);
}
