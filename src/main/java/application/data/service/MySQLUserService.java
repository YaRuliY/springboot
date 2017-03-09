package application.data.service;
import application.data.repa.UserRepository;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MySQLUserService implements UserService {
    private UserRepository userRepository;
    @Autowired public void setUserRepository(UserRepository userRepository){ this.userRepository = userRepository; }
    public MySQLUserService(){}

    public List<User> getAllUsers(){
        List<User> all = new ArrayList<>();
        for (User user: this.userRepository.findAll())
            all.add(user);
        return all;
    }

    public User findByLogin(String login){
        for (User user: this.userRepository.findAll())
            if(user.getLogin().equals(login)) return user;
        return null;
    }

    public void save(User user){ userRepository.save(user); }
}