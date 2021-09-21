package ru.itis;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DriverRepository implements CrudRepository<Driver, Long> {

    private final JdbcTemplate jdbcTemplate;
    private final static String SQL_SELECT_ALL = "select * from drivers;";
    private final static String SQL_INSERT = "insert into drivers (first_name, last_name, age) VALUES (?, ?, ?);";
    private final static String SQL_SELECT_BY_ID = "select * from drivers where id = ?;";

    private final static String SQL_UPDATE_BY_ID = "update drivers set first_name = ?, last_name = ?, age = ? where id = ?;";
    private static final String DELETE_COURSE_STUDENT = "delete from drivers where id = ?;";

    private final RowMapper<Driver> driverRowMapper = (row, rowNumber) -> Driver.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    public DriverRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Driver> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, driverRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Driver> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, driverRowMapper);
    }

    @Override
    public Driver save(Driver item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setInt(3, item.getAge());
            return statement;
        }, keyHolder);
        item.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return item;
    }

    @Override
    public Optional<Driver> update(Long id, Driver item) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, item.getFirstName(), item.getLastName(), item.getAge(), id);
        return findById(id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_COURSE_STUDENT, id);
    }
}
