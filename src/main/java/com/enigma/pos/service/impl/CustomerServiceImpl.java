package com.enigma.pos.service.impl;

import com.enigma.pos.entity.Customer;
import com.enigma.pos.repository.CustomerRepository;
import com.enigma.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
            String id = "CTM"+getAll().size()+1;
            if (customer.getName() != null && customer.getPhone() != null){
                customerRepository.createCustommer(id, customer.getName(), customer.getPhone());
            }
            return customerRepository.findCustomerById(id);
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
                return getCustomerByPhone(customer.getPhone());
            }
            return null;
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
    public void deleteById(String id) {
        try {
            Customer customer = getCustomerByPhone(id);
            customerRepository.deleteCustomerById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
