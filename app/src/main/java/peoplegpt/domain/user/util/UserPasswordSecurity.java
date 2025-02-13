package peoplegpt.domain.user.util;

public interface UserPasswordSecurity {
    public String encryptPassword(String password);
    public String decryptPassword(String encryptedPassword);
}
