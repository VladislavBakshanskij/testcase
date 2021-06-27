package com.example.demo.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import com.example.demo.model.user.UserActionType;

import javax.persistence.*;
import java.time.Instant;
import java.util.Map;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "journal", schema = "keyauto")
@TypeDefs({
        @TypeDef(name = "user_action_type", typeClass = PostgreSQLEnumType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Type(type = "user_action_type")
    private UserActionType userActionType;

    @Column(name = "create_date")
    private Instant createDate = Instant.now();

    @Type(type = "jsonb")
    @Column(name = "current_state", columnDefinition = "jsonb")
    private Map<String, Object> currentState;
}
