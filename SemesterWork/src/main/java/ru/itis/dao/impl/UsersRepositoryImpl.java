package ru.itis.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.dao.UsersRepository;
import ru.itis.dto.UserDto;
import ru.itis.model.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {

    private final static String SQL_INSERT = "insert into users(first_name, last_name, age, password_hash, email, avatar_id) " +
            "values (?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "update users set first_name = ?, last_name = ?, age = ?, password_hash = ?, email = ?, avatar_id = ? where id = ?";
    private final static String SQL_UPDATE_AVATAR = "update users set avatar_id = ? where id = ?";
    private final static String SQL_SELECT_BY_ID = "select * from users where id = ?";
    private final static String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";
    private final static String SQL_SELECT_BY_NAME = "select * from users where lower(first_name) ~ LOWER(?) or lower(last_name) ~ LOWER(?)";
    private final static String SQL_SELECT_ALL = "select * from users";

    private final static String SQL_DELETE = "delete from users where id = ?;";

    private final RowMapper<User> rowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .age(row.getInt("age"))
                    .hashPassword(row.getString("password_hash"))
                    .email(row.getString("email"))
                    .avatarId(row.getLong("avatar_id") == 0 ? null : row.getLong("avatar_id"))
                    .build();

    private final RowMapper<UserDto> rowMapper2 = (row, rowNumber) ->
            UserDto.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .email(row.getString("email"))
                    .avatarId(row.getLong("avatar_id") == 0 ? null : row.getLong("avatar_id"))
                    .build();

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, rowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateAvatarForUser(Long userId, Long fileId) {
        jdbcTemplate.update(SQL_UPDATE_AVATAR,
                fileId, userId
        );
    }

    @Override
    public List<UserDto> findByFirstOrLastName(String name) {
        return jdbcTemplate.query(SQL_SELECT_BY_NAME, rowMapper2, name, name);
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public User save(User item) {
        if (item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getFirstName());
                statement.setString(2, item.getLastName());
                statement.setInt(3, item.getAge());
                statement.setString(4, item.getHashPassword());
                statement.setString(5, item.getEmail());
                if (item.getAvatarId() != null) {
                    statement.setLong(6, item.getAvatarId());
                } else {
                    statement.setNull(6, Types.NULL);
                }
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().longValue());
            }
        } else {
            jdbcTemplate.update(SQL_UPDATE,
                    item.getFirstName(),
                    item.getLastName(),
                    item.getAge(),
                    item.getHashPassword(),
                    item.getEmail(),
                    item.getAvatarId(),
                    item.getId()
            );
        }
        return item;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
}
