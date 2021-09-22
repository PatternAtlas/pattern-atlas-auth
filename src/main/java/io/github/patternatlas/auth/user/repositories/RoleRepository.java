package io.github.patternatlas.auth.user.repositories;

import io.github.patternatlas.auth.user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);
}
