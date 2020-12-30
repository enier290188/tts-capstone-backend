package com.tts.capstone.employee.controller;

import com.tts.capstone.employee.entity.EmployeeEntity;
import com.tts.capstone.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "${TTS_CAPSTONE_FRONTEND_URL}")
@RequestMapping("/api/employee")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeEntity>> getFindAll() {
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<EmployeeEntity>> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeEntity employeeEntity) {
        EmployeeEntity employeeEntityData = employeeService.create(employeeEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeEntityData.getId()).toUri();
        return ResponseEntity.created(uri).body(employeeEntityData);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeEntity> updateById(@RequestBody EmployeeEntity employeeEntity, @PathVariable Long id) {
        return ResponseEntity.ok().body(employeeService.updateById(id, employeeEntity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
