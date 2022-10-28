package com.springboot.hello.dao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class UserDao {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<User> rowMapper = (rs, rowNum)-> {
        return new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

    };
    public User findById(String id) throws SQLException {
        return this.jdbcTemplate.queryForObject("select * from users where id=?", rowMapper, id);
    }


    public int add(User user){
        return this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
                user.getId(), user.getName(), user.getPassword());

    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }

    public int deleteById(String id) {
        return this.jdbcTemplate.update("delete from users where id=?", id);
    }
}
