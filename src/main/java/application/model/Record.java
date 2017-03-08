package application.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Record {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String telephone;
    private String hometel;
    private String address;
    private String email;
    private int user_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHometel() {
        return hometel;
    }

    public void setHometel(String hometel) {
        this.hometel = hometel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Record(){}
    public Record(String name, String surname, String patronymic, String telephone,
                  String hometel, String address, String email, int user_id){
        this.name=name;
        this.surname = surname;
        this.patronymic=patronymic;
        this.telephone=telephone;
        this.hometel=hometel;
        this.address=address;
        this.email=email;
        this.user_id=user_id;
    }

    public int getId() { return id; }

    public boolean search(String key){
        try{
            return this.name.toLowerCase().contains(key.toLowerCase()) || this.name.toUpperCase().contains(key.toUpperCase()) ||
                    this.surname.toLowerCase().contains(key.toLowerCase()) || this.surname.toUpperCase().contains(key.toUpperCase()) ||
                    this.telephone.toLowerCase().contains(key.toLowerCase()) || this.telephone.toUpperCase().contains(key.toUpperCase()) ||
                    this.hometel.toLowerCase().contains(key.toLowerCase()) || this.hometel.toUpperCase().contains(key.toUpperCase());
        }
        catch (NullPointerException e){ return false; }
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setId(int id){ this.id = id; }
}