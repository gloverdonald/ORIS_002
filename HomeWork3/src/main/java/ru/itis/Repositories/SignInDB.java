package ru.itis.Repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.Models.User;

import javax.sql.DataSource;
import java.util.Optional;

public class SignInDB {

    private final JdbcTemplate jdbcTemplate;

    public SignInDB(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final static String SQL_SELECT_BY_NAME = "select * from users where name = ?;";

    private final RowMapper<User> passwordRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .password(row.getString("password"))
            .avatarId(row.getInt("avatar_id"))
            .build();

    public Optional<User> findUser(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, passwordRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
