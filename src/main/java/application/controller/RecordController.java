package application.controller;
import application.model.Record;
import application.repa.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RecordController {
    private RecordService recordService;
    @Autowired
    public RecordController(RecordService recordService) { this.recordService = recordService; }

    @RequestMapping(value = "/get/records", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Record> getRecords() { return recordService.getRecords(); }

    @RequestMapping(value = "/get/record/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Record getRecordByID(@PathVariable("id")int id) { return recordService.getRecordByID(id); }

    @RequestMapping(value = "/search/{skey}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Record> searchRecords(@PathVariable("skey") String skey) { return recordService.searchRecords(skey); }

    @RequestMapping(value = "/add/record", method = RequestMethod.POST)
    public @ResponseBody String addRecord(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("telephone") String telephone,
            @RequestParam("hometel") String hometel,
            @RequestParam("address") String address,
            @RequestParam("email") String email){
        this.recordService.save(new Record(name,surname,patronymic,telephone,hometel,address,email));
        return "Record is writed";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteRecord(@PathVariable("id") int id){
        this.recordService.delete(id);
        return "Record is deleted";
    }

    @RequestMapping(value = "/edit/record/{id}", method = RequestMethod.POST)
    public @ResponseBody String editRecord(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("telephone") String telephone,
            @RequestParam("hometel") String hometel,
            @RequestParam("address") String address,
            @RequestParam("email") String email){
        this.recordService.edit(id, new Record(name,surname,patronymic,telephone,hometel,address,email));
        return "Record is edited";
    }
}
