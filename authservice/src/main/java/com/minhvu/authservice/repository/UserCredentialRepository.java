package com.minhvu.authservice.repository;

import com.minhvu.authservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserCredentialRepository  extends MongoRepository<User,String> {
    Optional<User> findByName(String username);

}
