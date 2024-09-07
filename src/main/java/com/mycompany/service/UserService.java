package com.mycompany.service;

import com.mycompany.repository.UserRepository;
import com.mycompany.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("UnnecessaryLocalVariable")

@Service
public class UserService{

    @Autowired
    private UserRepository repo;

    public void save(User user) {
        repo.save(user);
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }

    public User findByEmail(String email){
        User user = repo.findByEmail(email);
        return user;
    }
}