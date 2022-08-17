package bcd.function;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Block implements Serializable {

    private String currentHash;
    private String previoushash;

    private List<String> data;
    private long timestamp;

    public List<String> getData(){
        return data;

    }

    public void setData(List<String> data){
        this.data = data;
    }

    public String getCurretHash(){
        return currentHash;
    }

    public String getPrevioushash(){
        return previoushash;
    }

    public void setCurrentHash( String currentHash){
        this.currentHash =currentHash;
    }

    public void setPrevioushash(String previoushash){
        this.previoushash = previoushash;
    }


    public static void main(String[] args){


    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
    public String blockHashCode(byte[] data, String prehash, long timestamp){
        return  Hashing.hash(
                data +prehash +timestamp,"SHA-256");
    }

    private static byte[]genByteArr (List<String>a){
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        ObjectOutputStream out;
        if(a !=null){
           try{

           } catch (Exception e) {

           }
        }
        return null;
    }
}