package com.peoplegpt.demo.domain.comment.repository;

import java.sql.PreparedStatement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;

import javax.sql.DataSource;

import com.peoplegpt.demo.domain.comment.model.entity.Comment;
import com.peoplegpt.demo.domain.global.model.entity.DataStatus;

@Repository
public class CommentRepository {

    private static JdbcTemplate jdbcTemplate;
    private static final Logger logger = LogManager.getLogger(CommentRepository.class);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("deprecation")
    public List<Comment> findCommentsByPostId(long postId) {
        String sql = "SELECT * FROM Comment WHERE post_id = ? AND status = 'ACTIVE'";
        try {
            return jdbcTemplate.query(sql,
                    new Object[]{ postId },
                    (rs, rowNum) -> {
                        return Comment.builder()
                                .commentId(rs.getLong("comment_id"))
                                .userId(rs.getLong("user_id"))
                                .postId(rs.getLong("post_id"))
                                .content(rs.getString("content"))
                                .status(DataStatus.valueOf(rs.getString("status")))
                                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                .build();
                    });
        } catch (Exception e) {
            logger.error("Failed to find comments by postId", e);
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public Comment findCommentByCommentId(long commentId) {
        String sql = "SELECT * FROM Comment WHERE comment_id = ? AND status = 'ACTIVE'";
        try {
            return jdbcTemplate.queryForObject(sql,
                    new Object[]{ commentId },
                    (rs, rowNum) -> {
                        return Comment.builder()
                                .commentId(rs.getLong("comment_id"))
                                .userId(rs.getLong("user_id"))
                                .postId(rs.getLong("post_id"))
                                .content(rs.getString("content"))
                                .status(DataStatus.valueOf(rs.getString("status")))
                                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                .build();
                    });
        } catch (DataAccessException e) {
            logger.error("Failed to find comment by commentId: " + commentId, e);
            return null;
        }
    }

    @SuppressWarnings("null")
    public long addComment(long userId, long postId, String content) {
        String sql = "INSERT INTO Comment (user_id, post_id, content) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setLong(2, postId);
                ps.setString(3, content);
                return ps;
            }, keyHolder);
            
            return keyHolder.getKey().longValue();
        } catch (DataAccessException e) {
            logger.error("Failed to add comment", e);
            return -1;
        }
    }

    public boolean deleteComment(long commentId) {
        String sql = "UPDATE Comment SET status = 'INACTIVE' WHERE comment_id = ?";
        try {
            jdbcTemplate.update(sql, commentId);
        } catch (DataAccessException e) {
            logger.error("Failed to delete comment: " + commentId, e);
            return false;
        }
        return true;
    }

    public boolean updateComment(long commentId, String content) {
        String sql = "UPDATE Comment SET content = ? WHERE comment_id = ?";
        try {
            jdbcTemplate.update(sql, content, commentId);
        } catch (DataAccessException e) {
            logger.error("Failed to update comment: " + commentId, e);
            return false;
        }
        return true;
    }
}
