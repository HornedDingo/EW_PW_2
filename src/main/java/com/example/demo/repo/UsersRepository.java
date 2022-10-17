package com.example.demo.repo;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {
    List<User> findByUsernameContains(String title);
}
