package com.enigma.pos.service;

import com.enigma.pos.entity.Employee;
import com.enigma.pos.model.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    Employee create(Employee employee);
    EmployeeResponse getByEmail(String email);
    List<EmployeeResponse> getAll();
    void deleteByEmail(String email);
}
