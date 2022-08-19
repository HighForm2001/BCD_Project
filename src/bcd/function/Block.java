package bcd.function;

import bcd.config.GeneralOperation;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Block implements Serializable {
    private Header header;
    public Header getHeader(){return header;}
    public class Header implements Serializable{
        private int index;
        private String currHash, prevHash;
        private long timeStamp;
        public String getCurrHash(){return currHash;}
        public String getPrevHash(){return prevHash;}
        public void setIndex(int index){this.index = index;}
        public int getIndex(){return index;}
        public void setCurrHash(String currHash){this.currHash = currHash;}
        public void setPrevHash(String prevHash){this.prevHash = prevHash;}
        public long getTimeStamp(){return timeStamp;}
        public void setTimeStamp(long timeStamp){this.timeStamp = timeStamp;}
        @Override
        public String toString(){
            return "Header [index = " + this.index + ", currHash = "
                    + currHash + ", prevHash = " + prevHash + ", timeStamp = "
                    + timeStamp + "]";
        }
    }
    private StudentRecord record;
    public Block(String prevHash){
        header = new Header();
        header.setTimeStamp(new Timestamp(System.currentTimeMillis()).getTime());
        header.setPrevHash(prevHash);
        String blockHash =  Hashing.hash(getBytes(), GeneralOperation.getSha_Algo());
        header.setCurrHash((blockHash));
    }

    private byte[] getBytes() {
        try(ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteStream);)
        {
            out.writeObject(this);
            return byteStream.toByteArray();
        }catch(Exception e) {
            System.out.println("Error in Block line 44");
            e.printStackTrace();
            return null;
        }
    }
    public void setRecord(StudentRecord record){this.record = record;}
    public StudentRecord getRecord(){return record;}
}