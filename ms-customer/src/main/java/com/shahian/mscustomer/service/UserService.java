package com.shahian.mscustomer.service;

import com.shahian.mscustomer.model.User;
import com.shahian.mscustomer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User update(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("User not found"));
        user.setMobile(updatedUser.getMobile());
        user.setEmail(updatedUser.getEmail());

        return userRepository.save(user);
    }
    public void delete(User user) {
        User user1 = userRepository.findById(user.getId()).orElse(null);
        if (user1 !=null){
            userRepository.delete(user1);
        }
    }
}