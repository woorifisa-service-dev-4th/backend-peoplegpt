CREATE DATABASE peoplegpt
    DEFAULT CHARACTER SET = 'utf8mb4';

USE peoplegpt;

CREATE TABLE User (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    role ENUM('STUDENT', 'MENTOR', 'ADMIN') NOT NULL DEFAULT 'STUDENT',
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Post (
    post_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    category ENUM('QNA', 'CODESHARE', 'DAILY') NOT NULL,
    filter ENUM('AI', 'CLOUD', 'SERVICE') NOT NULL,
    tag ENUM('PROJECT', 'STUDY', 'LANGUAGE', 'ETC'),
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);
CREATE TABLE Comment (
    comment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (post_id) REFERENCES Post(post_id)
);