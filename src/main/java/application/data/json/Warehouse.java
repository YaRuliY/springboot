package application.data.json;
import application.model.Record;
import application.model.User;
import java.util.List;

public class Warehouse {
    private List<Record> records;
    private List<User> users;
    public Warehouse(){}
    public List<Record> getRecords(){ return records; }
    public List<User> getUsers(){ return users; }
}
