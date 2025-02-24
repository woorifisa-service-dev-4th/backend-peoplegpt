package com.peoplegpt.demo.domain.comment.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.peoplegpt.demo.domain.comment.model.entity.Comment;
import com.peoplegpt.demo.domain.global.model.entity.DataStatus;

public class CommentRepository {
    private static final Logger logger = LogManager.getLogger(CommentRepository.class);
    private static final String COMMENT_DATA_FILE = "comment_data.txt";

    private List<Comment> comments = parseCommentData();

    public List<Comment> getComments(){
        return comments;
    }

    private List<Comment> parseCommentData() {
        List<Comment> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();

        try (
            InputStream inputStream = classLoader.getResourceAsStream(COMMENT_DATA_FILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: " + COMMENT_DATA_FILE);
            }            

            String line;
            while((line = br.readLine()) != null) {
                String[] commentData = line.split(",");
                long commentId = Long.parseLong(commentData[0]); 
                long userId = Long.parseLong(commentData[1]);
                long postId = Integer.parseInt(commentData[2]);
                String content = commentData[3];
                LocalDateTime createdAt = LocalDateTime.parse(commentData[4]);
                DataStatus status = DataStatus.valueOf(commentData[5]);
                
                Comment comment = new Comment(commentId, postId, userId, content, createdAt, status);
                result.add(comment);
            }
        } catch (IOException e) {
            logger.error("Failed to read comment data file", e);
            throw new RuntimeException("Failed to read comment data file");
        }

        return result;
    }

    public long generateCommentId() {
        return comments.stream()
                .mapToLong(Comment::getCommentId)
                .max()
                .orElse(0) + 1;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        logger.info("New comment added: " + comment.getCommentId());
    }

    public List<Comment> findCommentsByPostId(long postId){
        return comments.stream()
                .filter(comment -> comment.getPostId() == postId && comment.getStatus() != DataStatus.INACTIVE)
                .collect(Collectors.toList());
    }
    
    public Comment findCommentByCommentId(long commentId){
        return comments.stream()
                .filter(comment -> comment.getCommentId() == commentId && comment.getStatus() != DataStatus.INACTIVE)
                .findFirst()
                .orElse(null);
    }

    public void updateComment(Comment request) {
        Comment comment = findCommentByCommentId(request.getCommentId());
        if (comment == null) {
            logger.info("Comment not found: " + request.getCommentId());
            return;
        }
        comment.updateContent(request.getContent());
        logger.info("Comment updated: " + request.getCommentId());
    }
}
