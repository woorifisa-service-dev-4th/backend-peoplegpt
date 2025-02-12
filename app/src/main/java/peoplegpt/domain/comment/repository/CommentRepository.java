package peoplegpt.domain.comment.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.comment.model.entity.Comment;
import peoplegpt.domain.global.model.entity.DataStatus;

public class CommentRepository {
    private static final String COMMENT_DATA_PATH = "main/resourse/comment_data.txt";
    private static final Logger logger = LogManager.getLogger(CommentRepository.class);

    private List<Comment> comments = parseCommentData();

    public List<Comment> getComments(){
        return comments;
    }

    private List<Comment> parseCommentData() {
        List<Comment> comments = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(COMMENT_DATA_PATH))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] commentData = line.split(",");
                int id = Integer.parseInt(commentData[0]); 
                long userId = Long.parseLong(commentData[1]);
                int postId = Integer.parseInt(commentData[2]);
                String content = commentData[3];
                LocalDateTime createdAt = LocalDateTime.parse(commentData[5]);
                DataStatus status = DataStatus.valueOf(commentData[6]);
                
                Comment comment = new Comment(id, postId, userId, content, createdAt, status);
                comments.add(comment);
            }
        } catch (IOException e) {
            logger.error("Failed to read comment data file", e);
            throw new RuntimeException("Failed to read comment data file");
        }

        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        logger.info("New comment added: " + comment);
        return;
    }

    public List<Comment> findCommentsByPostId(int postId){
        return comments.stream()
                .filter(comment -> comment.getPostId() == postId && comment.getStatus() != DataStatus.INACTIVE)
                .collect(Collectors.toList());
    }
    
    public Comment findCommentById(int id){
        return comments.stream()
                .filter(comment -> comment.getCommentId()==id&& comment.getStatus() != DataStatus.INACTIVE)
                .findFirst()
                .orElse(null);
    }

    public void updateComment(Comment updatedComment) {
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).getCommentId() == updatedComment.getCommentId()) {
                comments.set(i, updatedComment);
                return;
            }
        }
    }
}
