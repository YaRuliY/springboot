package application.data.json;
import application.data.json.Warehouse;
import application.model.Record;
import application.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class JSONRepository{
    @Value("${json.file-source}")
    private String fileSource;
    private Warehouse warehouse;
    public Warehouse getWarehouse(){ return warehouse; }
    public JSONRepository(){}

    @PostConstruct
    public void postConstruct() {
        try{ readFromFile(); }
        catch(IOException e){ e.printStackTrace(); }
    }

    //-----------RECORD-CRUD-------------------
    public List<Record> findAllRecords() throws IOException {
        readFromFile();
        return this.warehouse.getRecords();
    }

    public void saveRecord(Record record) throws IOException {
        this.warehouse.getRecords().add(record);
        this.persisteIntoFile();
    }

    public void deleteRecord(int id) throws IOException {
        Iterator<Record> iterator = this.warehouse.getRecords().iterator();
        while (iterator.hasNext()){
            if(iterator.next().getId() == id) iterator.remove();
        }
        this.persisteIntoFile();
    }

    public void editRecord(int id, Record record) throws IOException {
        this.deleteRecord(id);
        this.saveRecord(record);
        this.persisteIntoFile();
    }

    //-----------USER-CRUD-------------------
    public List<User> findAllUsers() throws IOException {
        readFromFile();
        return this.warehouse.getUsers();
    }

    public void saveUser(User user) throws IOException {
        this.warehouse.getUsers().add(user);
        this.persisteIntoFile();
    }

    //-----------File-Operation---------------------
    public void persisteIntoFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(new JsonParser().parse(gson.toJsonTree(warehouse).toString()));
        Files.write(Paths.get(this.fileSource), json.getBytes());
    }

    public void readFromFile() throws IOException {
        try{ this.warehouse = new Gson().fromJson(new JsonReader(new FileReader(this.fileSource)), Warehouse.class); }
        catch (FileNotFoundException e) {
            boolean flag = new File(this.fileSource).createNewFile();
            String structure =
                    "{\n" +
                    "  \"records\": [\n" +
                    "  ],\n" +
                    "  \"users\": [\n" +
                    "  ]\n" +
                    "}";
            Files.write(Paths.get(this.fileSource), structure.getBytes());
            if(flag) readFromFile();
        }
    }
}