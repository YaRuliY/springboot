package application.data.service;
import application.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User findByLogin(String login);
    void save(User user);
}