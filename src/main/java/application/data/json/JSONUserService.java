package application.data.json;

import application.data.service.IUserService;
import application.model.User;
import java.util.List;

public class JSONUserService implements IUserService{
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public void save(User user) {

    }
}