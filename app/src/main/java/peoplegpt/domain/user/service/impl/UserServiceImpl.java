package peoplegpt.domain.user.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.request.SignUpRequest;
import peoplegpt.domain.user.model.dto.response.SignInResponse;
import peoplegpt.domain.user.model.dto.response.SignUpResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;
import peoplegpt.domain.user.model.entity.User;
import peoplegpt.domain.user.repository.UserRepository;
import peoplegpt.domain.user.service.UserService;

public class UserServiceImpl implements UserService {

    // 임시 데이터 베이스 역할
    private final UserRepository userRepository;
    private final List<User> users;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.users = userRepository.getUsers();
    }
    
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public SignInResponse signIn(SignInRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        if (user == null || user.getUserStatus() == DataStatus.INACTIVE) {
            logger.info(request.getEmail(), "User not found");
            throw new RuntimeException("User not found");
        }

        String password = request.getPassword();
        boolean check = user.checkPassword(password);

        if (!check) {
            logger.info(request.getEmail(), "Password not matched");
            throw new RuntimeException("Password not matched");
        }

        SignInResponse response = new SignInResponse(user.getEmail());
        return response;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        boolean isExist = userRepository.isExistUserByEmail(request.getEmail());

        if (isExist) {
            logger.info(request.getEmail(), "User already exists");
            throw new RuntimeException("User already exists");
        }
        
        long userId = userRepository.generateUserId();
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();

        User user = new User(userId, email, password, name);

        users.add(user);

        SignUpResponse response = new SignUpResponse(user.getEmail());
        return response;
    }

    @Override
    public UserResponse getUser(GetUserRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        if (user == null || user.getUserStatus() == DataStatus.INACTIVE) {
            logger.info(request.getEmail(), "User not found");
            throw new RuntimeException("User not found");
        }
        
        UserResponse response = new UserResponse(user.getUserId(), user.getEmail(), user.getName(), user.getUserRole(), user.getCreatedAt());
        return response;
    }
    
}
