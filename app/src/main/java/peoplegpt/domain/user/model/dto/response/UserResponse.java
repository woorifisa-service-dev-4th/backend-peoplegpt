package peoplegpt.domain.user.model.dto.response;

import java.time.LocalDateTime;

import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.user.model.entity.UserRole;

public class UserResponse {
    private final long userId;
    private final String email;
    private final String name;
    private final UserRole role;
    private final LocalDateTime createdAt;

    public UserResponse(long userId, String email, String name, UserRole role, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
