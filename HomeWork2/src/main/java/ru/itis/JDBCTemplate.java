package ru.itis;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Objects;
import java.util.Optional;

public class JDBCTemplate {
    private final JdbcTemplate jdbcTemplate;

    public JDBCTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("mstpas123");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/hw2");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final static String SQL_SELECT_BY_NAME = "select * from users where username = ?;";

    private final RowMapper<User> passwordRowMapper = (row, rowNumber) -> User.builder()
            .username(row.getString("username"))
            .password(row.getString("password"))
            .build();

    public Optional<String> findProfile(String name) {
        try {
            return Optional.ofNullable(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, passwordRowMapper, name)).getPassword());
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private final static String SQL_INSERT = "insert into users(username,password) values (?,?);";

    public void save(String name, String password) {
        jdbcTemplate.update(SQL_INSERT, name, password);
    }
}
