package com.mycompany.service;

import com.mycompany.model.UserInquiry;
import com.mycompany.repository.UserInquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserInquiryService {

    private final UserInquiryRepository userInquiryRepository;

    @Autowired
    public UserInquiryService(UserInquiryRepository userInquiryRepository) {
        this.userInquiryRepository = userInquiryRepository;
    }

    @Transactional
    public UserInquiry saveUserInquiry(UserInquiry userInquiry) {
        return userInquiryRepository.save(userInquiry);
    }

    public List<UserInquiry> getAllUserInquiry(){
        return userInquiryRepository.findAll();
    }

    public void deleteById(int id){
        userInquiryRepository.deleteById(id);
    }
}