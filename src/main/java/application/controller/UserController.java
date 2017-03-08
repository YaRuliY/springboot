package application.controller;
import application.data.json.JSONUserService;
import application.data.service.UserService;
import application.model.User;
import application.validation.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class UserController {
    private /*UserService*/JSONUserService userService;
    @Autowired public void setUserService(JSONUserService userService){ this.userService = userService; }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody public List<User> getUsers(){ return userService.getAllUsers(); }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody public String addUser(
            @RequestParam("username") String login,
            @RequestParam("password") String pass,
            @RequestParam("confirm") String confirm,
            @RequestParam("fio") String fio){
        if(!(pass.equals(confirm))){
            return "Password and confirm don't match!";
        }
        else if(!(Util.validLogin(login))){
            return "Login is incorrect!";
        }
        else if(pass.length() < 5){
            return "Password must be more 4 characters";
        }
        else if(fio.length() < 5){
            return "Initials must be more 4 characters";
        }
        else {
            userService.save(new User(login, pass, fio));
            return "Hell Yeah!<br>You are new user!";
        }
    }
}