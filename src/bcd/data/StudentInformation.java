package bcd.data;

import java.io.Serializable;
import java.util.Random;

public class StudentInformation implements Serializable {
    private String ID;
    private String name;
    private Gender gender;
    private String contact;
    private String address;
    private String email;
    private String dob; //Date of Birth
    private Marital marital;

    public StudentInformation(String ID, String name, Gender gender, String contact, String address, String email, String dob, Marital marital) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.marital = marital;
    }
    public StudentInformation(){
        ID = "1";
        name = "cincai";
        gender = Gender.MALE;
        contact = "111";
        address = "jalan";
        email = "@m.com";
        dob = "1/1/22";
        marital = Marital.DIVORCED;
    }
    public StudentInformation(String name, String ID){ //for auto generation purpose
        this.ID = ID;
        this.name = name;
        Random random = new Random();
        if(random.nextInt()%2==0)
            gender = Gender.MALE;
        else
            gender = Gender.FEMALE;
        contact = "111";
        address = "jalan";
        email = "@m.com";
        dob = "1/1/22";
        int random_marital = random.nextInt();
        if(random_marital%5 == 0)
            marital = Marital.DIVORCED;
        else if(random_marital%5 == 1)
            marital = Marital.MARRIED;
        else if(random_marital%5 == 2)
            marital = Marital.SINGLE;
        else if(random_marital%5 == 3)
            marital = Marital.SEPARATED;
        else
            marital = Marital.WIDOWED;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }
    public Marital getMarital(){return marital;}

    @Override
    public String toString() {
        return "StudentInformation{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", marital=" + marital +
                '}';
    }
}
