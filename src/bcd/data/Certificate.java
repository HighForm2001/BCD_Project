package bcd.data;

public class Certificate {
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
}
