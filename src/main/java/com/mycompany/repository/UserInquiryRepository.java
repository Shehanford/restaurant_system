package com.mycompany.repository;

import com.mycompany.model.UserInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInquiryRepository extends JpaRepository<UserInquiry, Long> {
}