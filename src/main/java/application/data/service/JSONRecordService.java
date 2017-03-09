package application.data.service;
import application.data.json.JSONRepository;
import application.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JSONRecordService implements RecordService {
    private JSONRepository repository;
    @Autowired public void setRepository(JSONRepository repository){ this.repository = repository; }

    public List<Record> getRecordsByUserID(int userID) {
        List<Record> all = new ArrayList<>();
        try {
            all.addAll(this.repository.findAllRecords().stream().filter(
                    record -> record.getUser_id() == userID).collect(Collectors.toList()));
        }
        catch (IOException e){ e.printStackTrace(); }
        return all;
    }

    public Record getRecordByID(int id) {
        for (Record record: this.repository.getWarehouse().getRecords()){ if (record.getId() == id) return record; }
        return null;
    }

    public List<Record> searchRecords(String skey, int userID) {
        return this.getRecordsByUserID(userID).stream().filter(
                record -> record.search(skey)).collect(Collectors.toList());
    }

    public void save(Record record){
        try {
            record.setId(this.repository.getWarehouse().getRecords().size() + 1);
            this.repository.saveRecord(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            this.repository.deleteRecord(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(int id, Record record){
        try {
            this.repository.editRecord(id, record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}