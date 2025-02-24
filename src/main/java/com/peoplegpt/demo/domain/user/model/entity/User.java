package com.peoplegpt.demo.domain.user.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.peoplegpt.demo.domain.global.model.entity.DataStatus;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private long userId;
    private final String email;
    private String password;
    private final String name;
    private UserRole role;
    private DataStatus status;
    private final LocalDateTime createdAt;

    public User(long userId, String email, String password, String name) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = UserRole.STUDENT;
        this.status = DataStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
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
