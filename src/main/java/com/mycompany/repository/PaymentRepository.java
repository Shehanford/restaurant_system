package com.mycompany.repository;

import com.mycompany.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
