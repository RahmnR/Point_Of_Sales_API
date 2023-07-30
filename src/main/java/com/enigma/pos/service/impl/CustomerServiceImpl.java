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
    @Transactional(rollbackOn = Exception.class)
    public Customer create( Customer customer) {
        try {
            return customerRepository.save(customer);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Customer updateById(Customer customer) {
        try {
            Customer customerById = customerRepository.findCustomerById(customer.getId());
            if (customerById != null) {
                customerRepository.updateCustomer(customer.getName(), customer.getPhone(), customer.getId());
            }
            return getCustomerByPhone(customer.getPhone());
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAllCustomer();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteByPhone(String phone) {
        Customer customer = getCustomerByPhone(phone);
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }

}
