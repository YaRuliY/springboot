package application.data.service;
import application.model.User;
import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findByLogin(String login);
    void save(User user);
}