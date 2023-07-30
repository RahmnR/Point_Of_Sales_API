package com.enigma.pos.service.impl;

import com.enigma.pos.entity.Employee;
import com.enigma.pos.model.response.EmployeeResponse;
import com.enigma.pos.repository.EmployeeRepository;
import com.enigma.pos.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponse getByEmail(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email).orElse(null);
        return EmployeeResponse.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAllEmployee().stream().map(employee -> {
            return EmployeeResponse.builder()
                    .name(employee.getName())
                    .email(employee.getEmail())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findEmployeeByEmail(email);
        if (employee.isPresent()){
            employeeRepository.deleteEmployeeByEmail(email);
        }
    }
}
