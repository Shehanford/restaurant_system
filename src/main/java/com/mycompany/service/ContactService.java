package com.mycompany.service;

import com.mycompany.model.Contact;
import com.mycompany.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }
}
