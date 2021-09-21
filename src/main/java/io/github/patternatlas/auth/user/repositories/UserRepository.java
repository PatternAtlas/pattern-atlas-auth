package io.github.patternatlas.auth.user.repositories;

import io.github.patternatlas.auth.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(UUID id);
    UserEntity findByName(String name);
}
