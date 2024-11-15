package com.brainstorm.Egen.ai.service.impl;

import com.brainstorm.Egen.ai.data.CustomerDTO;
import com.brainstorm.Egen.ai.entity.Customer;
import com.brainstorm.Egen.ai.repository.CustomerRepository;
import com.brainstorm.Egen.ai.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customer.getAddress());
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        customerRepository.delete(optionalCustomer.get());
    }
}
