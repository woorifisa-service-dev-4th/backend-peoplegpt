package com.peoplegpt.demo.domain.user.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.peoplegpt.demo.domain.global.model.entity.DataStatus;
import com.peoplegpt.demo.domain.user.model.entity.User;
import com.peoplegpt.demo.domain.user.model.entity.UserRole;

@Repository
public class UserRepository {

    private static JdbcTemplate jdbcTemplate;
    private final Logger logger = LogManager.getLogger(UserRepository.class);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("deprecation")
    public User findUserByUserId(long userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return this.jdbcTemplate.queryForObject(sql, 
            new Object[] { userId },
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return User.builder()
                            .userId(rs.getLong("user_id"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .name(rs.getString("name"))
                            .role(UserRole.valueOf(rs.getString("role")))
                            .status(DataStatus.valueOf(rs.getString("status")))
                            .createdAt(LocalDateTime.parse(rs.getString("created_at")))
                            .build();
                }
        });
    }
    
    @SuppressWarnings("deprecation")
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        return this.jdbcTemplate.queryForObject(sql, 
            new Object[] { email },
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return User.builder()
                            .userId(rs.getLong("user_id"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .name(rs.getString("name"))
                            .role(UserRole.valueOf(rs.getString("role")))
                            .status(DataStatus.valueOf(rs.getString("status")))
                            .createdAt(LocalDateTime.parse(rs.getString("created_at")))
                            .build();
                }
        });
    }

    @SuppressWarnings("deprecation")
    public boolean isExistUserByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class) > 0;
    }

    public void add(User user) {
        String sql = "INSERT INTO user (email, password, name, role, status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getName(), user.getRole().name(),
                user.getStatus().name(), user.getCreatedAt().toString());
    }
}
