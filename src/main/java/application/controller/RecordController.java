package application.controller;
import application.data.json.JSONRecordService;
import application.model.Record;
import application.data.service.RecordService;
import application.data.service.UserService;
import application.validation.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RecordController {
    private /*RecordService*/JSONRecordService recordService;
    private UserService userService;

    @Autowired public void setRecordService(/*RecordService*/JSONRecordService recordService) { this.recordService = recordService; }
    @Autowired public void setUserService(UserService userService) { this.userService = userService; }

    @RequestMapping(value = "/get/records", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Record> getRecords(HttpServletRequest request) {
        int userID = userService.findByLogin(request.getRemoteUser()).getId();
        return recordService.getRecordsByUserID(userID);
    }

    @RequestMapping(value = "/get/record/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Record getRecordByID(@PathVariable("id")int id) { return recordService.getRecordByID(id); }

    @RequestMapping(value = "/search/{skey}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Record> searchRecords(
            @PathVariable("skey") String skey,
            HttpServletRequest request) {
        int userID = userService.findByLogin(request.getRemoteUser()).getId();
        return recordService.searchRecords(skey, userID);
    }

    @RequestMapping(value = "/add/record", method = RequestMethod.POST)
    public @ResponseBody String addRecord(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("telephone") String telephone,
            @RequestParam("hometel") String hometel,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            HttpServletRequest request){
        int userID = userService.findByLogin(request.getRemoteUser()).getId();
        telephone = telephone.trim();
        hometel = hometel.trim();

        if(name.length() < 4 || surname.length() < 4 || patronymic.length() < 4){
            return "Input data must be more then 4 characters!";
        }
        else if(!(Util.validTelephoneNumber(telephone))){
            System.out.println("telephone: "+telephone+"; " + telephone.length());
            return "Telephone number is incorect";
        }
        else {
            if(hometel.length() > 0)
                if(!Util.validTelephoneNumber(hometel))
                    return "Home telephone number is incorect";

            if(email.length() > 0)
                if(!Util.validEmain(email))
                    return "E-main is incorect";

            this.recordService.save(new Record(name,surname,patronymic,telephone,hometel,address,email,userID));
            return "Record is writed";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteRecord(@PathVariable("id") int id){
        this.recordService.delete(id);
        return "Record is deleted";
    }

    @RequestMapping(value = "/edit/record/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String editRecord(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("telephone") String telephone,
            @RequestParam("hometel") String hometel,
            @RequestParam("address") String address,
            @RequestParam("email") String email,
            HttpServletRequest request){
        int userID = userService.findByLogin(request.getRemoteUser()).getId();
        telephone = telephone.trim();
        hometel = hometel.trim();

        if(name.length() < 4 || surname.length() < 4 || patronymic.length() < 4){
            return "Input data must be more then 4 characters!";
        }
        else if(!(Util.validTelephoneNumber(telephone))){
            System.out.println("telephone: "+telephone+"; " + telephone.length());
            return "Telephone number is incorect";
        }
        else {
            if(hometel.length() > 0)
                if(!Util.validTelephoneNumber(hometel))
                    return "Home telephone number is incorect";

            if(email.length() > 0)
                if(!Util.validEmain(email))
                    return "E-main is incorect";

            this.recordService.edit(id, new Record(name,surname,patronymic,telephone,hometel,address,email,userID));
            return "Record is edited";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "Hello!";
    }
}
