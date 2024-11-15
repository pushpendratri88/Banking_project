package com.brainstorm.Egen.ai.controller;

import com.brainstorm.Egen.ai.data.CustomerDTO;
import com.brainstorm.Egen.ai.data.ResponseDTO;
import com.brainstorm.Egen.ai.entity.Customer;
import com.brainstorm.Egen.ai.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customerList = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.CREATED).body(customerList);
    }


    @GetMapping(value = "/getCustomer")
    public ResponseEntity<Customer> getCustomer(@RequestParam Long id ){
        Optional<Customer> customer = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer.get());
    }


    @PostMapping(value = "/CreateCustomer")
    public ResponseEntity<ResponseDTO> createCustomer(@RequestBody CustomerDTO customerDTO ){
        customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201","created"));
    }

}
