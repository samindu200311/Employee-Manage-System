package edu.icet.ecom.service.impl;

import edu.icet.ecom.dto.Employee;
import edu.icet.ecom.model.EmployeeEntity;
import edu.icet.ecom.repository.EmployeeRepository;
import edu.icet.ecom.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        repository.deleteById(id);
    }


}
