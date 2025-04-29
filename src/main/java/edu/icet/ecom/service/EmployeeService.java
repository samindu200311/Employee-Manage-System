package edu.icet.ecom.service;

import edu.icet.ecom.dto.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);

    List<Employee> getAll();

    void deleteEmployee(Integer id);

    void updateEmployee(Employee employee);
}
