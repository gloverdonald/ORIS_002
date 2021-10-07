package ru.itis.Repositories;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class SignUpDB {
    private final JdbcTemplate jdbcTemplate;

    public SignUpDB(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final static String SQL_INSERT = "insert into users(name,password) values (?,?);";

    public void save(String name, String password) {
        jdbcTemplate.update(SQL_INSERT, name, password);
    }
}
