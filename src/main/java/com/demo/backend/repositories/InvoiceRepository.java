package com.demo.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.backend.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
