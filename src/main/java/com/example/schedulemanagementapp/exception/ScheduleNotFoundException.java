package com.example.schedulemanagementapp.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
