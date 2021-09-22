package io.github.patternatlas.auth.user.repositories;

import io.github.patternatlas.auth.user.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {

    Privilege findByName(String name);
}
