package edu.icet.ecom.controller;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {

    final EmployeeService service;

    @PostMapping("/add-employee")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee){
        service.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully.");
    }



}
