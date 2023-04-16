package com.shahian.mscustomer.repository;

import com.shahian.mscustomer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}