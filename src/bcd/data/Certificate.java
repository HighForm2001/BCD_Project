package bcd.data;

import java.io.Serializable;

public class Certificate implements Serializable {
    private final String CGPA;
    private final String Academic_Degrees;
    private final String Class;

    public String getCGPA() {
        return CGPA;
    }

    public String getAcademic_Degrees() {
        return Academic_Degrees;
    }

    public String getclass() {
        return Class;
    }


    public Certificate(String CGPA, String Academic_Degrees, String Class){
        this.CGPA = CGPA;
        this.Academic_Degrees = Academic_Degrees;
        this.Class = Class;
    }
    public Certificate(){
        CGPA = "4.0";
        Academic_Degrees = "Degree";
        Class = "Software Engineering";

    }


    @Override
    public String toString() {
        return "\n======================\nCertificate\n======================\n" +
                "CGPA = '" + CGPA + '\'' +
                "\nAcademic_Degrees = '" + Academic_Degrees + '\'' +
                "\nClass = '" + Class + '\'' +
                '\n';
    }
}
