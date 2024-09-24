package com.example.employeemanagement.employee_management_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public String viewEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employee-list";
    }

    //for adding new details
    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employees";
    }   
    
    //used for editing the current details(added to enhance)
    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit-employee"; // New template for editing
    }

    //used for updatng employee details 
    @PostMapping("/update")
    public String updateEmployee(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employees";
    }

    // used for Delete employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        service.deleteEmployee(id);
        return "redirect:/employees";
    }

    // used to get the average
    @GetMapping("/average")
    public String calculateAverageSalary(Model model) {
        double averageSalary = service.calculateAverageSalary();
        model.addAttribute("averageSalary", averageSalary);
        return "average-salary"; 
    }

    
}

