package com.example.demo.app;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import org.hibernate.dialect.PostgreSQL10Dialect;

public class CustomPostgreSQLDialect extends PostgreSQL10Dialect {
    public CustomPostgreSQLDialect() {
        this.registerColumnType(1111, JsonNodeBinaryType.class.getName());
        this.registerHibernateType(1111, JsonNodeStringType.class.getName());
    }
}
