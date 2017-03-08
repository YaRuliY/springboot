package application.data.repa;
import application.data.json.Warehouse;
import application.data.service.RecordService;
import application.data.service.UserService;
import application.model.Record;
import application.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class JSONRepository{
    private final String source = "maindb.json";
    private Warehouse warehouse;
    private RecordService recordService;
    private UserService userService;
    public JSONRepository(){ this.warehouse = new Warehouse(); }
    public Warehouse getWarehouse(){ return warehouse; }

    @Autowired public void setRecordService(RecordService recordService) { this.recordService = recordService; }
    @Autowired public void setUserService(UserService userService) { this.userService = userService; }

    public List<Record> findAllRecords() throws FileNotFoundException {
        this.warehouse = new Gson().fromJson(new JsonReader(new FileReader(this.source)), Warehouse.class);
        return this.warehouse.getRecords();
    }

    public List<User> findAllUsers() throws FileNotFoundException {
        this.warehouse = new Gson().fromJson(new JsonReader(new FileReader(this.source)), Warehouse.class);
        return this.warehouse.getUsers();
    }

    public void saveRecord(Record record){}
    public void deleteRecord(int id){}
    public void editRecord(int id, Record record){}
    public void saveUser(User user){}
    public void deleteUser(int id){}
    public void editUser(int id, User user){}

    public void persisteIntoFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        warehouse.setRecords(recordService.getAllRecords());
        warehouse.setUsers(userService.getAllUsers());
        String json = gson.toJson(new JsonParser().parse(gson.toJsonTree(warehouse).toString()));
        Files.write(Paths.get(this.source), json.getBytes());
    }
}