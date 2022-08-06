package com.example.loginapplication.repository;

import com.example.loginapplication.model.MyUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<MyUsers,Long> {
    MyUsers findByUsername(String username);
}

