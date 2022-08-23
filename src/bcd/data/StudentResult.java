package bcd.data;

import java.io.Serializable;

public class StudentResult implements Serializable {
    private String module;
    transient private Double credit;
    private String result;
    private String grade;
    private String cgpa;

    public StudentResult(String module, String result, String grade, String name, String studentID) {
        this.module = module;
        this.credit = credit;
        this.result = result;
        this.grade = grade;
        this.cgpa = cgpa;
    }
    public StudentResult(){
        module = "testing";
        credit = 20.0;
        result = "80";
        grade = "A";
        cgpa = "4.0";
    }

    public String getModule() {
        return module;
    }

    public double getCredit() {
        return credit;
    }

    public String getResult() {
        return result;
    }

    public String getGrade() {
        return grade;
    }

    public String getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "StudentResult{" +
                "module='" + module + '\'' +
                ", credit=" + credit +
                ", result='" + result + '\'' +
                ", grade='" + grade + '\'' +
                ", cgpa='" + cgpa + '\'' +
                '}';
    }
}
