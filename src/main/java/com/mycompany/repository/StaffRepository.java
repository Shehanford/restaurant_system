package com.mycompany.repository;

import com.mycompany.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByEmail(String email);

}
