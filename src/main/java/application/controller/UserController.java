package application.controller;
import application.repa.UserService;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody public List<User> getUsers(){ return userService.findAll(); }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    @ResponseBody public String addUser(
            @RequestParam("login") String login,
            @RequestParam("pass") String pass,
            @RequestParam("fio") String fio){
        userService.save(new User(login, pass, fio));
        return "Hell Yeah!";
    }

    /*@RequestMapping(value = "/logout/performe", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/login";
    }*/

    @RequestMapping(value = "/login/performe", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("LOGIN: {" + request.getParameter("username") + ": " + request.getParameter("password") + "}");
        return "redirect:/main";
    }
}