package application.controller;
import application.data.repa.JSONRepository;
import application.model.Record;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.List;

@Controller
public class JsonController {
    private JSONRepository repository;
    @Autowired public void setRepository(JSONRepository repository){ this.repository = repository; }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody public String persisteData() throws IOException {
        repository.persisteIntoFile();
        return "data is save";
    }

    @RequestMapping(value = "/json/record", method = RequestMethod.GET)
    @ResponseBody public List<Record> getRecords() throws IOException {
        return repository.findAllRecords();
    }
    @RequestMapping(value = "/json/user", method = RequestMethod.GET)
    @ResponseBody public List<User> getUsers() throws IOException {
        return repository.findAllUsers();
    }
}