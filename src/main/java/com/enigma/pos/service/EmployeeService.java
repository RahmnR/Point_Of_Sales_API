package com.enigma.pos.service;

import com.enigma.pos.entity.Employee;
import com.enigma.pos.model.request.EmployeeRequest;
import com.enigma.pos.model.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse getByEmail(String email);
    List<EmployeeResponse> getAll();
    EmployeeResponse update(EmployeeRequest request);
    void deleteByEmail(String email);
}
