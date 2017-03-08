package application.repa;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
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