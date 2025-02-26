package com.peoplegpt.demo.domain.post.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    public Post updateQNAPost(long postId, String title, String content, Category category, ClassFilter filter, Tag tag) {
        String sql;
        Object[] params;
        if((category == null) && (filter == null)) {
            sql ="UPDATE Post SET title = ?, content = ?, category = ? WHERE post_id = ?";
            params = new Object[] {
                title,
                content,
                category.toString(),
                postId
            };
        } else if(tag == null) {
            sql = "UPDATE Post SET title = ?, content = ?, category = ?, filter = ? WHERE post_id = ?";
            params = new Object[] {
                title,
                content,
                category.toString(),
                filter.toString(),
                postId
            };
        } else if(filter == null) {
            sql = "UPDATE Post SET title = ?, content = ?, category = ?, tag = ? WHERE post_id = ?";
            params = new Object[] {
                title,
                content,
                category.toString(),
                tag.toString(),
                postId
            };
        } else {
            sql = "UPDATE Post SET title = ?, content = ?, category = ?, filter = ?, tag = ? WHERE post_id = ?";
            params = new Object[] {
                title,
                content,
                category.toString(),
                filter.toString(),
                tag.toString(),
                postId
            };
        }

        try {
            jdbcTemplate.update(sql, params);
            return findPostByPostId(postId);
        } catch (Exception e) {
            logger.error("Failed to update QNA post", e);
            throw new RuntimeException("Failed to update QNA post", e);
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

    @SuppressWarnings("deprecation")
    public long postOwnerIdByPostId(long postId){
        String sql = "SELECT user_id FROM Post WHERE post_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { postId }, Long.class);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Failed to get post owner id", e);
            return -1;
        }
    }

    public Post saveQNAPost(long userId, String title, String content, Category category, ClassFilter filter, Tag tag) {
        String sql;
        if((tag == null) && (filter == null)) {
            sql = "INSERT INTO Post (user_id, title, content, category) " +
                    "VALUES (?, ?, ?, ?)";
        } else if(tag == null) {
            sql = "INSERT INTO Post (user_id, title, content, category, filter) " +
                    "VALUES (?, ?, ?, ?, ?)";
        } else if(filter == null) {
            sql = "INSERT INTO Post (user_id, title, content, category, tag) " +
                    "VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO Post (user_id, title, content, category, filter, tag) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        }
        
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setString(2, title);
                ps.setString(3, content);
                ps.setString(4, category.toString());
                if((filter != null) && (tag != null)) {
                    ps.setString(5, filter.toString());
                    ps.setString(6, tag.toString());
                } else if(filter != null) {
                    ps.setString(5, filter.toString());
                } else if(tag != null) {
                    ps.setString(5, tag.toString());
                }
                return ps;
            }, keyHolder);
            
            Number key = keyHolder.getKey();
            if (key != null) {
                long postId = key.longValue();
                return findPostByPostId(postId);
            } else {
                logger.error("Failed to get generated key after saving QNA post");
                throw new SQLException("Failed to get generated key");
            }
        } catch (Exception e) {
            logger.error("Failed to save QNA post", e);
            throw new RuntimeException("Failed to save QNA post", e);
        }
    }

    public Post saveMentorPost(long userId, String title, String content, Category category, ClassFilter filter) {
        String sql = "INSERT INTO Post (user_id, title, content, category, filter) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, userId);
                ps.setString(2, title);
                ps.setString(3, content);
                ps.setString(4, category.toString());
                ps.setString(5, DataStatus.ACTIVE.toString());
                ps.setTimestamp(6, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                return ps;
            }, keyHolder);
            
            Number key = keyHolder.getKey();
            if (key != null) {
                long postId = key.longValue();
                return findPostByPostId(postId);
            } else {
                logger.error("Failed to get generated key after saving mentor post");
                throw new SQLException("Failed to get generated key");
            }
        } catch (Exception e) {
            logger.error("Failed to save mentor post", e);
            throw new RuntimeException("Failed to save mentor post", e);
        }
    }

    @SuppressWarnings("deprecation")
    public Post updatePost(long postId, String title, String content) {
        String sql = "UPDATE Post SET title = ?, content = ? WHERE post_id = ?";
        Object[] params = new Object[] {
            title,
            content,
            postId
        };
        
        try {
            jdbcTemplate.update(sql, params);
            return findPostByPostId(postId);
        } catch (Exception e) {
            logger.error("Failed to update post", e);
            throw new RuntimeException("Failed to update post", e);
        }
    }

    @SuppressWarnings("deprecation")
    public int deletePost(long postId) {
        String sql = "UPDATE Post SET status = ? WHERE post_id = ?";
        Object[] params = new Object[] {
            DataStatus.INACTIVE.toString(),
            postId
        };
        
        try {
            return jdbcTemplate.update(sql, params);
        } catch (Exception e) {
            logger.error("Failed to delete post", e);
            throw new RuntimeException("Failed to delete post", e);
        }
    }
}

