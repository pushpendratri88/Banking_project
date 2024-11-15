package com.brainstorm.Egen.ai.repository;

import com.brainstorm.Egen.ai.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
