package application.data.service;
import application.data.json.JSONRepository;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONUserService implements UserService {
    private JSONRepository repository;
    @Autowired public void setRepository(JSONRepository repository){ this.repository = repository; }
    public JSONUserService(){}

    public List<User> getAllUsers(){
        List<User> all = new ArrayList<>();
        try{ all.addAll(this.repository.findAllUsers()); }
        catch (IOException e){ e.printStackTrace(); }
        return all;
    }

    public User findByLogin(String login) {
        try {
            for (User user: this.repository.findAllUsers())
                if(user.getLogin().equals(login)) return user;
        }
        catch (IOException e){ e.printStackTrace(); }
        return null;
    }

    public void save(User user) {
        try{
            user.setId(this.getAllUsers().size() + 1);
            this.repository.saveUser(user);
        }
        catch (IOException e){ e.printStackTrace(); }
    }
}