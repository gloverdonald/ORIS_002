package ru.itis.dao.impl;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.dao.FollowRepository;

import ru.itis.dto.UserDto;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class FollowRepositoryImpl implements FollowRepository {
    private final JdbcTemplate jdbcTemplate;

    public FollowRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final static String SQL_SELECT_FOLLOWERS  = "select f.profile_id as id, p.first_name as first_name, p.last_name as last_name, p.avatar_id\n" +
            "from follows f\n" +
            "         join users p on f.profile_id = p.id\n" +
            "where following_id = ?;";

    private final static String SQL_SELECT_FOLLOWING= "select p.id as id, p.first_name as first_name, p.last_name as last_name, p.avatar_id as avatar_id\n" +
            "from follows f\n" +
            "         join users p on f.following_id = p.id\n" +
            "where profile_id = ?;";

    private final static String SQL_SAVE = "insert into follows (profile_id, following_id) values (?, ?);";

    private final static String SQL_RECOMMENDATIONS = "select distinct p.id as id, p.first_name as first_name, p.last_name as last_name, p.avatar_id as avatar_id\n" +
            "from follows f\n" +
            "         join follows f2 on f.following_id = f2.profile_id\n" +
            "         join users p on f2.following_id = p.id\n" +
            "where f.profile_id = ?  and not EXISTS (select * from follows f3 where f3.profile_id = f.profile_id and f3.following_id = p.id);";
    //language=SQL
    private final static String SQL_DELETE = "delete from follows where profile_id = ? and following_id = ?;";


    private final RowMapper<UserDto> rowMapper = (row, rowNumber) ->
            UserDto.builder()
                    .id(row.getLong("id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .avatarId(row.getLong("avatar_id") == 0 ? null : row.getLong("avatar_id"))
                    .build();

    @Override
    public List<UserDto> followers(Long id) {
        return jdbcTemplate.query(SQL_SELECT_FOLLOWERS, rowMapper, id);
    }

    @Override
    public List<UserDto> following(Long id) {
        return jdbcTemplate.query(SQL_SELECT_FOLLOWING, rowMapper, id);
    }

    @Override
    public void follow(Long profile_id, Long following_id) {
        jdbcTemplate.update(SQL_SAVE, profile_id, following_id);
    }

    @Override
    public void unfollow(Long profile_id, Long following_id) {
        jdbcTemplate.update(SQL_DELETE, profile_id, following_id);
    }

    @Override
    public List<UserDto> recommendations(Long id) {
        return jdbcTemplate.query(SQL_RECOMMENDATIONS, rowMapper, id);
    }
}
