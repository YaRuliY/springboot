package application.data.json;
import application.data.repa.JSONRepository;
import application.data.service.IUserService;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JSONUserService implements IUserService{
    private JSONRepository repository;
    @Autowired
    public void setRepository(JSONRepository repository){ this.repository = repository; }

    @Override
    public List<User> getAllUsers(){
        List<User> all = new ArrayList<>();
        try{ all.addAll(this.repository.findAllUsers()); }
        catch (IOException e){ e.printStackTrace(); }
        return all;
    }

    @Override
    public User findByLogin(String login) {
        try {
            for (User user: this.repository.findAllUsers())
                if(user.getLogin().equals(login)) return user;
        }
        catch (IOException e){ e.printStackTrace(); }
        return null;
    }

    @Override
    public void save(User user) {
        try{ this.repository.saveUser(user); }
        catch (IOException e){ e.printStackTrace(); }
    }
}