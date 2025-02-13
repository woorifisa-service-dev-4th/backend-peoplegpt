package peoplegpt.domain.user.model.entity;

import java.time.LocalDateTime;

import peoplegpt.domain.global.model.entity.DataStatus;

public class User {
    private long userId;
    private final String email;
    private String password;
    private final String name;
    private UserRole role;
    private DataStatus status;
    private final LocalDateTime createdAt;

    // STUDENT 등록 시 사용
    public User(long userId, String email, String password, String name) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = UserRole.STUDENT;
        this.status = DataStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // ADMIN, MENTOR 등록 시 사용
    public User(long userId, String email, String password, String name, UserRole role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = DataStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // Database에서 가져올 때 사용
    public User(long userId, String email, String password, String name, UserRole role, DataStatus status, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;
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

    public DataStatus getUserStatus() {
        return status;
    }

    public UserRole getUserRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeUserRole(UserRole role) {
        this.role = role;
    }

    public void deleteUser() {
        this.status = DataStatus.INACTIVE;
    }

}
