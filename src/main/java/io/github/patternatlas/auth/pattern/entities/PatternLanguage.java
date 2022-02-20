package io.github.patternatlas.auth.pattern.entities;

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

@Entity
@Data
//@NoArgsConstructor
public class PatternLanguage implements Serializable {
    
    @Id
    @GeneratedValue(generator = "pg-uuid")
    private UUID id;

    public PatternLanguage() {

    }
}
