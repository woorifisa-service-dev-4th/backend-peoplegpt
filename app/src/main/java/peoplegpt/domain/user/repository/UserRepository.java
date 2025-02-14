package peoplegpt.domain.user.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.user.model.entity.User;
import peoplegpt.domain.user.model.entity.UserRole;

public class UserRepository {
    private static final Logger logger = LogManager.getLogger(UserRepository.class);
    private static final String USER_DATA_FILE = "user_data.txt"; // 리소스 파일 이름

    private List<User> users = parseUsersData();

    public List<User> getUsers() {
        return users;
    }

    private List<User> parseUsersData() {
        List<User> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();

        try (
            InputStream inputStream = classLoader.getResourceAsStream(USER_DATA_FILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: " + USER_DATA_FILE);
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                long userId = Long.parseLong(userData[0]);
                String email = userData[1];
                String password = userData[2];
                String name = userData[3];
                UserRole role = UserRole.valueOf(userData[4]);
                DataStatus status = DataStatus.valueOf(userData[5]);
                LocalDateTime createdAt = LocalDateTime.parse(userData[6]);

                User user = new User(userId, email, password, name, role, status, createdAt);
                result.add(user);
            }
        } catch (IOException e) {
            logger.error("Failed to read user data file: " + USER_DATA_FILE, e);
            throw new RuntimeException("Failed to read user data file: " + USER_DATA_FILE);
        }

        return result;
    }

    public User findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public boolean isExistUserByEmail(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    public long generateUserId() {
        return users.stream()
                .mapToLong(User::getUserId)
                .max()
                .orElse(0) + 1;
    }

    public void add(User user) {
        users.add(user);
    }
}
