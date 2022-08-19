package bcd.data;

import java.io.Serializable;

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
