package application.security;
import application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import application.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UDService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userService.findByLogin(username);
        List<GrantedAuthority> gas = new ArrayList<>();
        gas.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), true, true, true, true, gas);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}