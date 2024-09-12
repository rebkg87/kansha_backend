package com.example.kansha.repositories;

import com.example.kansha.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User, Integer> {
    Optional <User> findByEmail (String email);
    Optional<User> findById(Integer id);
}
