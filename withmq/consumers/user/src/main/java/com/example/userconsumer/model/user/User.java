package com.example.userconsumer.model.user;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "auth_user", schema = "keyauto")
@TypeDefs({
        @TypeDef(
                name = "user_post",
                typeClass = PostgreSQLEnumType.class
        ),
        @TypeDef(
                name = "user_role",
                typeClass = PostgreSQLEnumType.class
        )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Type(type = "user_role")
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "post", nullable = false)
    @Type(type = "user_post")
    private UserPost post;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;
}
