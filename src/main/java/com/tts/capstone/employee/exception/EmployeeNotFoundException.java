package com.tts.capstone.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee with id='" + id + "'.");
    }
}
