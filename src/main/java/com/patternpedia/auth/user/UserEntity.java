package com.patternpedia.auth.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "pg-uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.MEMBER;

    private String mail;

    private String name;

    @JsonIgnore
    private String password;

}
