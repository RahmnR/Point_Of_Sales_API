package com.enigma.pos.controller;

import com.enigma.pos.entity.Customer;
import com.enigma.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerService.create(customer);
    }
    @GetMapping
    public List<Customer> viewAll(){
        return customerService.getAll();
    }

    @GetMapping("/{phone}")
    public  Customer getByPhone(@PathVariable String phone){
        return customerService.getCustomerByPhone(phone);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer){
        return customerService.updateById(customer);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        try {
            customerService.deleteById(id);
            return "Success";
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(e.getStatus());
        }
    }
}
