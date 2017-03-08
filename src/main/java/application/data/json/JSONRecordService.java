package application.data.json;
import application.data.repa.JSONRepository;
import application.data.service.IRecordService;
import application.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JSONRecordService implements IRecordService{
    private JSONRepository repository;
    @Autowired public void setRepository(JSONRepository repository){ this.repository = repository; }

    @Override
    public List<Record> getRecordsByUserID(int userID) {
        List<Record> all = new ArrayList<>();
        try {
            all.addAll(this.repository.findAllRecords().stream().filter(
                    record -> record.getUser_id() == userID).collect(Collectors.toList()));
        }
        catch (FileNotFoundException e){ e.printStackTrace(); }
        return all;
    }

    @Override
    public Record getRecordByID(int id) {
        for (Record record: this.repository.getWarehouse().getRecords()){ if (record.getId() == id) return record; }
        return null;
    }

    @Override
    public List<Record> searchRecords(String skey, int userID) {
        return this.getRecordsByUserID(userID).stream().filter(
                record -> record.search(skey)).collect(Collectors.toList());
    }

    @Override
    public void save(Record record){ this.repository.saveRecord(record); }

    @Override
    public void delete(int id){ this.repository.deleteRecord(id); }

    @Override
    public void edit(int id, Record record){ this.repository.editRecord(id, record); }
}