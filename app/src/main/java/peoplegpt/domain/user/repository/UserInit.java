package peoplegpt.domain.user.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.user.model.entity.User;
import peoplegpt.domain.user.model.entity.UserRole;

public class UserInit {
    private static final String USER_DATA_PATH = "main/resources/user_data.txt";
    private static final Logger logger = LogManager.getLogger(UserInit.class);
    
    public List<User> parseUsersData() {
        List<User> users = new ArrayList<>();

        // 파일에서 데이터를 읽어와서 User 객체로 변환 후 users에 추가
        try(BufferedReader br = new BufferedReader(new FileReader(USER_DATA_PATH))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                long userId = Long.parseLong(userData[0]);
                String email = userData[1];
                String password = userData[2];
                String name = userData[3];
                UserRole role = UserRole.valueOf(userData[4]);
                DataStatus status = DataStatus.valueOf(userData[5]);
                LocalDateTime createdAt = LocalDateTime.parse(userData[6]);

                User user = new User(userId, email, password, name, role, status, createdAt);
                users.add(user);
            }
        } catch (IOException e) {
            logger.error("Failed to read user data file", e);
            throw new RuntimeException("Failed to read user data file");
        }

        return users;
    }
}
