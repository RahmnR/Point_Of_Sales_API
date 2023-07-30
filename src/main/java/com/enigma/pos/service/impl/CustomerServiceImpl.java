package com.enigma.pos.service.impl;

import com.enigma.pos.entity.Customer;
import com.enigma.pos.model.response.CustomerResponse;
import com.enigma.pos.repository.CustomerRepository;
import com.enigma.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer create( Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getByPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone).orElse(null);
    }

    @Override
    @Transactional
    public Customer updateById(Customer customer) {

        customerRepository.updateCustomer(customer.getName(), customer.getPhone(), customer.getId());
        return customer;
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAllCustomer().stream().map(customer -> {
            return CustomerResponse.builder().name(customer.getName()).Phone(customer.getPhone()).build();
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteByPhone(String phone) {
        Customer customer = getByPhone(phone);
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }
}
