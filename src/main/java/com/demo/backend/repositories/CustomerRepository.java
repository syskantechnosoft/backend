package com.demo.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.backend.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
