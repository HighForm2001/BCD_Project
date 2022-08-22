package bcd.data;

import java.io.Serializable;

public class Certificate implements Serializable {
    private final String CGPA;
    private final String Academic_Degrees;
    private final String Class;
    private final String Name;
    private final String StudentID;

    public String getCGPA() {
        return CGPA;
    }

    public String getAcademic_Degrees() {
        return Academic_Degrees;
    }

    public String getclass() {
        return Class;
    }


    public String getName() {
        return Name;
    }

    public String getStudentID() {
        return StudentID;
    }
    public Certificate(String CGPA, String Academic_Degrees, String Class, String Name, String StudentID){
        this.CGPA = CGPA;
        this.Academic_Degrees = Academic_Degrees;
        this.Class = Class;
        this.Name = Name;
        this.StudentID = StudentID;
    }
    public Certificate(){
        CGPA = "4.0";
        Academic_Degrees = "Degree";
        Class = "Software Engineering";
        Name = "Chin";
        StudentID = "TP059235";
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "CGPA='" + CGPA + '\'' +
                ", Academic_Degrees='" + Academic_Degrees + '\'' +
                ", Class='" + Class + '\'' +
                ", Name='" + Name + '\'' +
                ", StudentID='" + StudentID + '\'' +
                '}';
    }
}
