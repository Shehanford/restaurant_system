package com.mycompany;

import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setFirstName("Sachini");
        user.setLastName("Fernando");
        user.setEmail("sachinif@gmail.com");
        user.setPassword("1234");

        User saveUser = userRepository.save(user);
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListUsers(){
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUser(){
        Integer userid = 15;
        Optional <User> optionalUser = userRepository.findById(userid);

        User user = optionalUser.get();
        user.setLastName("Silva");
        userRepository.save(user);

        User updateUser = userRepository.findById(userid).get();
        Assertions.assertThat(updateUser.getLastName()).isEqualTo("Silva");
    }

    @Test
    public void testDeleteUser(){
        Integer userid = 20;
        userRepository.deleteById(userid);

        Optional <User> optionalUser = userRepository.findById(userid);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}