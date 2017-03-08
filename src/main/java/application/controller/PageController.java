package application.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PageController {
    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration(){ return "registration"; }

    @RequestMapping(value = {"main"}, method = RequestMethod.GET)
    public String main(){ return "main"; }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(){ return "add"; }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(){ return "edit"; }
}