package com.example.schedulemanagementapp.controller;

import com.example.schedulemanagementapp.dto.ScheduleRequestDto;
import com.example.schedulemanagementapp.dto.ScheduleResponseDto;
import com.example.schedulemanagementapp.entity.Schedule;
import com.example.schedulemanagementapp.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 추가
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedule(
            @RequestParam(required = false) String modifiedDate,
            @RequestParam(required = false) String author
    ) {
        List<ScheduleResponseDto> list = scheduleService.findSchedules(modifiedDate, author);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.findById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    // 선택한 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        ScheduleResponseDto schedule = scheduleService.updateSchedule(id, requestDto);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    // 선택한 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleByIdAndPassword(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto){
        scheduleService.deleteByIdAndPassword(id, requestDto.getPassword());
        return ResponseEntity.noContent().build();
    }
}
