package com.enigma.pos.service;

import com.enigma.pos.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getCustomerByPhone(String phone);
    List<Customer> getAll();
    Customer updateById(Customer customer);
    void deleteById(String id);
}
