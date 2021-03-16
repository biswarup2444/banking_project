package com.bankingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingservice.model.Customer;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
