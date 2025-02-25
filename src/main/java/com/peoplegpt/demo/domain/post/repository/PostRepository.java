package com.peoplegpt.demo.domain.post.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.global.model.entity.DataStatus;
import com.peoplegpt.demo.domain.post.model.entity.Category;
import com.peoplegpt.demo.domain.post.model.entity.Post;
import com.peoplegpt.demo.domain.post.model.entity.Tag;

@Repository
public class PostRepository {

    private static JdbcTemplate jdbcTemplate;
    private static final Logger logger = LogManager.getLogger(PostRepository.class);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Post> getPosts() {
        String sql = "SELECT * FROM Post WHERE status = 'ACTIVE' ORDER BY created_at DESC";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                return Post.builder()
                        .postId(rs.getLong("post_id"))
                        .userId(rs.getLong("user_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .category(Category.valueOf(rs.getString("category")))
                        .filter(ClassFilter.valueOf(rs.getString("filter")))
                        .tag(Tag.valueOf(rs.getString("tag")))
                        .status(DataStatus.valueOf(rs.getString("status")))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();
            });
        } catch (EmptyResultDataAccessException e) {
            logger.error("Failed to get posts", e);
            return null;
        }
    }

    // 게시글 목록 조회
    @SuppressWarnings("deprecation")
    public List<Post> getPostsByCategory(Category category) {
        String sql = "SELECT * FROM Post WHERE category = ? AND status = 'ACTIVE' ORDER BY created_at DESC";
        try {
            return jdbcTemplate.query(sql, new Object[] { category.toString() }, (rs, rowNum) -> {
                return Post.builder()
                        .postId(rs.getLong("post_id"))
                        .userId(rs.getLong("user_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .category(Category.valueOf(rs.getString("category")))
                        .filter(ClassFilter.valueOf(rs.getString("filter")))
                        .tag(Tag.valueOf(rs.getString("tag")))
                        .status(DataStatus.valueOf(rs.getString("status")))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();
            });
        } catch (EmptyResultDataAccessException e) {
            logger.error("Failed to get posts by category[", category, "] ", e);
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public Post findPostByPostId(long postId) {
        String sql = "SELECT * FROM Post WHERE post_id = ? AND status = 'ACTIVE'";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { postId }, (rs, rowNum) -> {
                return Post.builder()
                        .postId(rs.getLong("post_id"))
                        .userId(rs.getLong("user_id"))
                        .title(rs.getString("title"))
                        .content(rs.getString("content"))
                        .category(Category.valueOf(rs.getString("category")))
                        .filter(ClassFilter.valueOf(rs.getString("filter")))
                        .tag(Tag.valueOf(rs.getString("tag")))
                        .status(DataStatus.valueOf(rs.getString("status")))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();
            });
        } catch (EmptyResultDataAccessException e) {
            logger.error("Failed to find post by postId", e);
            return null;
        }
    }

}

