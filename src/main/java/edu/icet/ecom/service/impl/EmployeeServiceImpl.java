package edu.icet.ecom.service.impl;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.model.EmployeeEntity;
import edu.icet.ecom.repository.EmployeeRepository;
import edu.icet.ecom.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    final EmployeeRepository repository;
    final ModelMapper mapper;

    @Override
    public void addEmployee(Employee employee) {
        if (repository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        repository.save(mapper.map(employee, EmployeeEntity.class));
    }

    @Override
    public List<Employee> getAll() {
        List<EmployeeEntity> all = repository.findAll();
        List<Employee> employeeList = new ArrayList<>();

        all.forEach(EmployeeEntity ->{
            employeeList.add(mapper.map(EmployeeEntity, Employee.class));
        });

        return employeeList;
    }

    @Override
    public void deleteEmployee(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Employee with ID " + id + " does not exist");
        }
        repository.deleteById(id);
    }


    @Override
    public void updateEmployee(Employee employee) {
        EmployeeEntity existingEmployee = repository.findById(employee.getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + employee.getId() + " does not exist"));

        if (repository.findByEmail(employee.getEmail()).isPresent() &&
                !existingEmployee.getEmail().equals(employee.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        EmployeeEntity updatedEmployee = mapper.map(employee, EmployeeEntity.class);
        updatedEmployee.setCreatedAt(existingEmployee.getCreatedAt());
        updatedEmployee.setUpdatedAt(LocalDateTime.now());
        repository.save(updatedEmployee);
    }



}
