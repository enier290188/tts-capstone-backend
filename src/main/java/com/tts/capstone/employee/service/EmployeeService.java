package com.tts.capstone.employee.service;

import com.tts.capstone.employee.entity.EmployeeEntity;
import com.tts.capstone.employee.exception.EmployeeNotFoundException;
import com.tts.capstone.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> findById(Long id) {
        Optional<EmployeeEntity> employeeEntityData = employeeRepository.findById(id);
        if (employeeEntityData.isPresent()) {
            return employeeEntityData;
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity updateById(Long id, EmployeeEntity employeeEntity) {
        Optional<EmployeeEntity> employeeEntityData = findById(id);
        if (employeeEntityData.isPresent()) {
            employeeEntity.setId(employeeEntityData.get().getId());
            return employeeRepository.save(employeeEntity);
        }
        return employeeEntity;
    }

    public void deleteById(Long id) {
        Optional<EmployeeEntity> employeeEntityData = findById(id);
        if (employeeEntityData.isPresent()) {
            employeeRepository.deleteById(id);
        }
    }
}
