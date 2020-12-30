package com.tts.capstone.employee.initializer;

import com.tts.capstone.employee.entity.EmployeeEntity;
import com.tts.capstone.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
class EmployeeInitializer implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... strings) {
        ArrayList<EmployeeEntity> users = new ArrayList<>(
                Arrays.asList(
                        new EmployeeEntity("Jessica", "Smith", "Accountant"),
                        new EmployeeEntity("Adam", "Jones", "Programmer"),
                        new EmployeeEntity("David", "Williams", "Project Manager"),
                        new EmployeeEntity("Sarah", "Adams", "Accountant"),
                        new EmployeeEntity("Jennifer", "Jones", "Accountant"),
                        new EmployeeEntity("Mary", "Johnson", "Project Manager"),
                        new EmployeeEntity("Glen", "Galvan", "Accountant"),
                        new EmployeeEntity("Bridget", "Willis", "Accountant"),
                        new EmployeeEntity("Henry", "Sharp", "Accountant"),
                        new EmployeeEntity("Jaden", "Berg", "Programmer"),
                        new EmployeeEntity("Kevin", "Bourne", "Customer Relations"),
                        new EmployeeEntity("Celia", "Berg", "Project Manager"),
                        new EmployeeEntity("Jon", "Wu", "Accountant"),
                        new EmployeeEntity("Adelle", "Braun", "Programmer"),
                        new EmployeeEntity("Kerys", "Chen", "Project Manager"),
                        new EmployeeEntity("Holly", "Cain", "Accountant"),
                        new EmployeeEntity("Marcus", "Nelson", "Accountant"),
                        new EmployeeEntity("Elli", "Nelson", "Project Manager"),
                        new EmployeeEntity("Aaron", "Carson", "Accountant"),
                        new EmployeeEntity("Violet", "Abbot", "Customer Relations")
                )
        );
        employeeRepository.saveAll(users);
    }
}
