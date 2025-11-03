package com.example.batch.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<Customer, Long> {

}
