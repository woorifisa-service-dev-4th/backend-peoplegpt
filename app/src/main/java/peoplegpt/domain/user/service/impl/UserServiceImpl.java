package peoplegpt.domain.user.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.request.SignUpRequest;
import peoplegpt.domain.user.model.dto.response.SignResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;
import peoplegpt.domain.user.model.entity.User;
import peoplegpt.domain.user.repository.UserRepository;
import peoplegpt.domain.user.service.UserService;
import peoplegpt.domain.user.util.UserPasswordSecurity;

public class UserServiceImpl implements UserService {

    // 임시 데이터 베이스 역할
    private final UserRepository userRepository;
    private final UserPasswordSecurity userPasswordSecurity;
    public UserServiceImpl(UserRepository userRepository, UserPasswordSecurity userPasswordSecurity) {
        this.userRepository = userRepository;
        this.userPasswordSecurity = userPasswordSecurity;
    }

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public SignResponse signIn(SignInRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        if (user == null || user.getUserStatus() == DataStatus.INACTIVE) {
            logger.info(request.getEmail(), "User not found");
            System.out.println("User not found");
            return new SignResponse(null);
        }

        String password = request.getPassword();
        boolean check = user.checkPassword(password);

        if (!check) {
            logger.info(request.getEmail(), "Password not matched");
            System.out.println("Password not matched");
            return new SignResponse(null);
        }

        SignResponse response = new SignResponse(user.getEmail());
        System.out.println("Sign in success");
        return response;
    }

    @Override
    public SignResponse signUp(SignUpRequest request) {
        boolean isExist = userRepository.isExistUserByEmail(request.getEmail());

        if (isExist) {
            logger.info(request.getEmail(), "User already exists");
            System.out.println("User already exists");
            return new SignResponse(null);
        }
        
        long userId = userRepository.generateUserId();
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();
        String encryptedPassword = userPasswordSecurity.encryptPassword(password);
        System.out.println(userId + " " + name + " " + email + " " + password);

        User user = new User(userId, email, password, name);

        userRepository.add(user);

        SignResponse response = new SignResponse(user.getEmail());
        return response;
    }

    @Override
    public UserResponse getUser(GetUserRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        UserResponse response = new UserResponse(user.getUserId(), user.getEmail(), user.getName(), user.getUserRole(), user.getCreatedAt());
        return response;
    }
    
}
