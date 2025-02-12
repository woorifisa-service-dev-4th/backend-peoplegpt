package peoplegpt.domain.user.service;

import peoplegpt.domain.user.model.entity.User;

public interface UserService {
    public void createUser(User user);

    public User getUser(String userId);

    public boolean login(String userId, String password);

}
