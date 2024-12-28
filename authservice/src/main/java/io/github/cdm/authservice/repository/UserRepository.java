package io.github.cdm.authservice.repository;

import io.github.cdm.authservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);

    boolean existsByNameAllIgnoreCase(String name);
}
