package com.sarthak.Employee_Management_System.services;

import com.sarthak.Employee_Management_System.entities.EmployeeEntity;
import com.sarthak.Employee_Management_System.model.Employee;
import com.sarthak.Employee_Management_System.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    // Constructor
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    // Implementation part to create an Employee
    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        BeanUtils.copyProperties(employee, employeeEntity);

        employeeRepository.save(employeeEntity);

        return employee;
    }

    // Implementation part to fetch all employees
    @Override
    public List<Employee> getAllEmployees() {

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> new Employee(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail()))
                .toList();

        return employees;
    }

    @Override
    public boolean deleteById(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeEntity, employee);

        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());

        employeeRepository.save(employeeEntity);

        return employee;
    }

}
