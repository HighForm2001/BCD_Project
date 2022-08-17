package bcd.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentRecord {
    private XSSFSheet sheet;

    public StudentRecord() throws IOException{

    }

    public List<User> All (String name, int age, String email){
        try
        {
            List<User> records = new ArrayList<>();
            records.add(new User(name, age, email));
            return records;

        }catch (Exception e){
            return null;
        }
    }

}
