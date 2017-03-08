package application.repa;
import application.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordService {
    private RecordRepository recordRepository;
    @Autowired
    public RecordService(RecordRepository recordRepository) { this.recordRepository = recordRepository; }

    public List<Record> getRecords(){
        List<Record> all = new ArrayList<>();
        for (Record record: this.recordRepository.findAll())
            all.add(record);
        return all;
    }

    public List<Record> getRecordsByUserID(int userID){
        List<Record> all = new ArrayList<>();
        for (Record record: this.recordRepository.findAll())
            if(record.getUser_id() == userID) all.add(record);
        return all;
    }

    public Record getRecordByID(int id){ return this.recordRepository.findOne(id); }
    public void save(Record record) { recordRepository.save(record); }
    public void delete(int id){ recordRepository.delete(id); }

    public List<Record> searchRecords(String skey, int userID){
        return this.getRecordsByUserID(userID).stream().filter(record -> record.search(skey)).collect(Collectors.toList());
    }

    public void edit(int id, Record record){
        Record origin = recordRepository.findOne(id);
        origin.setAddress(record.getAddress());
        origin.setEmail(record.getEmail());
        origin.setHometel(record.getHometel());
        origin.setTelephone(record.getTelephone());
        origin.setName(record.getName());
        origin.setSurname(record.getSurname());
        origin.setPatronymic(record.getPatronymic());
        recordRepository.save(origin);
    }
}