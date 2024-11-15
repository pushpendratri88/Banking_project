package com.brainstorm.Egen.ai.service;

import com.brainstorm.Egen.ai.data.CustomerDTO;
import com.brainstorm.Egen.ai.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(CustomerDTO customerDTO);
    List<Customer> getAllCustomer();
    Optional<Customer> getCustomerById(Long id);
    void deleteCustomer(Long id);

}
