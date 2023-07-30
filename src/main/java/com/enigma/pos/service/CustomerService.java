package com.enigma.pos.service;

import com.enigma.pos.entity.Customer;
import com.enigma.pos.model.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getByPhone(String phone);
    List<CustomerResponse> getAll();
    Customer updateById(Customer customer);
    void deleteByPhone(String phone);
}
