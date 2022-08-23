package bcd.function;

import bcd.config.GeneralOperation;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;


public class Blockchain {
    //master-binary-file
    public static final String master_binary = GeneralOperation.getMaster_binary();
    public static final String record_file = GeneralOperation.getRecord_path();
    private static LinkedList<Block> DB = new LinkedList<>();//data structure

    //the very first block - genesis()
    public static void genesis(StudentRecord record){
        Block genesis = new Block("0",record);
        DB.add(genesis);
        Blockchain.presist();
    }


    public static void nextBlock(Block newBlock){
        DB = Blockchain.retrieve_chain();
        DB.add(newBlock);
        Blockchain.presist();
    }
    public static LinkedList<Block> retrieve_chain(){
        try(
            FileInputStream fis = new FileInputStream(master_binary);
            ObjectInputStream in = new ObjectInputStream(fis);
            )
        {
            return (LinkedList<Block>)in.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public static void presist(){
        try (
            FileOutputStream fos = new FileOutputStream(master_binary);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            )
        {
            out.writeObject(DB);
            System.out.println("Master file updated");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public  static  void distribute(){
        //convert chain to string using API
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(Blockchain.retrieve_chain());
        System.out.println(chain);
        try{
            File f = new File(record_file);
            Files.write(Paths.get(record_file),chain.getBytes(),StandardOpenOption.CREATE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}





