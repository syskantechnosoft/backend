package com.demo.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.backend.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
