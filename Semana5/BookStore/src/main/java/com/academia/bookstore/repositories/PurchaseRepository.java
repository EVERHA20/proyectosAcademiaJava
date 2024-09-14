package com.academia.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.bookstore.models.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}