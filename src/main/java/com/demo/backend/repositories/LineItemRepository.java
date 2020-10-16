package com.demo.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.backend.models.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Integer> {

}
