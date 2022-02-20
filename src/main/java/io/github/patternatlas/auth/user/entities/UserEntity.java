package io.github.patternatlas.auth.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.patternatlas.auth.user.entities.Role;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "pg-uuid")
    private UUID id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany()
    @JoinTable(
        name = "user_entity_roles",
        joinColumns = { @JoinColumn(name = "users_id") },
        inverseJoinColumns = { @JoinColumn(name = "roles_id") }
    )
    private Collection<Role> roles;

    @NaturalId(mutable = true)
    @Column(nullable = false, unique = true)
    private String email;

    @NaturalId(mutable = true)
    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public UserEntity(String name, String email, String password, Collection<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
