package com.alam.basicapi.controller;

import com.alam.basicapi.exceptionhandeling.ResourceNotFoundException;
import com.alam.basicapi.model.Employee;
import com.alam.basicapi.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    //No need for Exception handeling cuz returning
    @GetMapping("/list")
    public List<Employee> Employee()
    {
        return employeeRepository.findAll();
    }

    @GetMapping("/listOne/{id}")
    public Employee employeeListOne(@PathVariable int id) throws ResourceNotFoundException {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + id)));

        return employee.get();
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee)
    {
        employeeRepository.save(employee);
    }

    @PutMapping("/update/{id}")

    public void updateEmployee(@PathVariable int id, @RequestBody Employee emp) throws ResourceNotFoundException {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + id)));
        //.get cuz we are using optional class which handles null pointer exception
        employee.get().setFirstName(emp.getFirstName());
        employee.get().setLastName(emp.getLastName());
        employee.get().setAge(emp.getAge());
        employee.get().setSalary(emp.getSalary());
        employee.get().setEducation(emp.getEducation());

        employeeRepository.save(employee.get());

    }

    @DeleteMapping("/delete/{id}")

    public void deleteEmployee(@PathVariable int id) throws ResourceNotFoundException {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + id)));
        employeeRepository.delete(employee.get());
    }
}
