package application.data.service;
import application.model.Record;
import java.util.List;

public interface RecordService {
    List<Record> getRecordsByUserID(int userID);
    Record getRecordByID(int id);
    List<Record> searchRecords(String skey, int userID);
    void save(Record record);
    void delete(int id);
    void edit(int id, Record record);
}