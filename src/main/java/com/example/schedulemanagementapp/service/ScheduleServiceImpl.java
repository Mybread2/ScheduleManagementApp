package com.example.schedulemanagementapp.service;

import com.example.schedulemanagementapp.dto.ScheduleRequestDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;
import com.example.schedulemanagementapp.entity.Schedule;
import com.example.schedulemanagementapp.exception.InvalidPasswordException;
import com.example.schedulemanagementapp.exception.ScheduleNotFoundException;
import com.example.schedulemanagementapp.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        Schedule schedule = new Schedule(
                requestDto.getTodo(),
                requestDto.getAuthor(),
                requestDto.getPassword(),
                now,
                now
        );

        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    public List<ScheduleResponseDto> findSchedules(String modifiedDate, String author) {
        List<Schedule> schedules = scheduleRepository.findSchedules(modifiedDate, author);
        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("해당 일정이 존재하지 않습니다. ID = " + id));
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int updated = scheduleRepository.updateSchedule(
                id,
                dto.getTodo(),
                dto.getAuthor(),
                dto.getPassword(),
                now
        );

        if (updated == 0) {
            throw new InvalidPasswordException("비밀번호가 틀리거나 일정이 존재하지 않습니다.");
        }
        // 성공 시 최신 데이터 다시 조회해서 반환

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("일정 정보를 찾을 수 없습니다."));
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteByIdAndPassword(Long id, String password) {
        int deleted = scheduleRepository.ddeleteByIdAndPassword(id, password);
        if(deleted == 0){
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
    }
}
