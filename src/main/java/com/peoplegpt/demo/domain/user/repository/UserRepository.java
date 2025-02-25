package com.peoplegpt.demo.domain.user.repository;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public boolean existsById(Long userId) {
        String sql = "SELECT COUNT(*) FROM User WHERE user_id = ? AND status = 'ACTIVE'";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
            return count != null && count > 0;
        } catch (Exception e) {
            logger.error("Failed to check if user exists", e);
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public User findUserByUserId(long userId) {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, 
                new Object[] { userId },
                (rs, rowNum) -> {
                    return User.builder()
                            .userId(rs.getLong("user_id"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .name(rs.getString("name"))
                            .role(UserRole.valueOf(rs.getString("role")))
                            .status(DataStatus.valueOf(rs.getString("status")))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .build();
                });
        } catch (EmptyResultDataAccessException e) {
            logger.error("Failed to find user by userId", e);
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM User WHERE email = ?";
        try{
            return jdbcTemplate.queryForObject(sql, 
                new Object[] { email },
                (rs, rowNum) -> {
                    return User.builder()
                            .userId(rs.getLong("user_id"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .name(rs.getString("name"))
                            .role(UserRole.valueOf(rs.getString("role")))
                            .status(DataStatus.valueOf(rs.getString("status")))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .build();
                });
        } catch (EmptyResultDataAccessException e) {
            return null;

        }
    }

    @SuppressWarnings("deprecation")
    public boolean isExistUserByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM User WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class) > 0;
        } catch (EmptyResultDataAccessException e) {
            logger.error("Failed to check user existence by email", e);
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public UserRole getUserRole(long userId) {
        String sql = "SELECT role FROM User WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { userId }, UserRole.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Failed to get user role", e);
            return null;
        }
    }

    public void add(User user) {
        String sql = "INSERT INTO User (email, password, name, role) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getName(), UserRole.STUDENT.name());
        } catch (Exception e) {
            logger.error("Failed to add user", e);
        }
    }
}
