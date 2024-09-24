package com.example.employeemanagement.employee_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public double calculateAverageSalary() {
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) return 0.0;

        double totalSalary = employees.stream().mapToDouble(Employee::getSalary).sum();
        return totalSalary / employees.size();
    }

}

