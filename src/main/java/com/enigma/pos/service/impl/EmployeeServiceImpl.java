package com.enigma.pos.service.impl;

import com.enigma.pos.entity.Employee;
import com.enigma.pos.model.request.EmployeeRequest;
import com.enigma.pos.model.response.EmployeeResponse;
import com.enigma.pos.repository.EmployeeRepository;
import com.enigma.pos.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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

    @Transactional(rollbackOn = Exception.class)
    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = employeeRepository.save(Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build());

        return EmployeeResponse.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }

    @Override
    public EmployeeResponse getByEmail(String email) {
        Employee employee = getEmploye(email);
        return EmployeeResponse.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .build();
    }

    @Override
    public Employee getEmploye(String email) {
        return employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found");
                });
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
    public EmployeeResponse update(EmployeeRequest request) {
        try {
            Employee employee = employeeRepository.findEmployeeByEmail(request.getEmail()).get();
            employeeRepository.updateEmployeeBy(request.getName(), request.getEmail());
            return EmployeeResponse.builder().build();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteByEmail(String email) {
        try {
            Optional<Employee> employee = employeeRepository.findEmployeeByEmail(email);
            if (employee.isPresent()) {
                employeeRepository.deleteEmployeeByEmail(email);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
