package com.enigma.pos.controller;

import com.enigma.pos.model.request.EmployeeRequest;
import com.enigma.pos.model.response.EmployeeResponse;
import com.enigma.pos.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest request){
        return employeeService.create(request);
    }

    @GetMapping
    public List<EmployeeResponse> viewAllEmployee(){
        return employeeService.getAll();
    }
}
