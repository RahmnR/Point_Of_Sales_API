package com.enigma.pos.controller;

import com.enigma.pos.model.request.EmployeeRequest;
import com.enigma.pos.model.response.EmployeeResponse;
import com.enigma.pos.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
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
    public List<EmployeeResponse> viewAllEmployee(@RequestParam(name = "email",required = false)String email){
        if (email==null)return employeeService.getAll();
        return List.of(employeeService.getByEmail(email));
    }

    @PutMapping
    public EmployeeResponse update(@RequestBody EmployeeRequest request){
        return employeeService.update(request);
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable String email){
        try{
            employeeService.deleteByEmail(email);
            return "Success";
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found");
        }
    }
}

