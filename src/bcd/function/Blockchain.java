package bcd.function;

import bcd.config.GeneralOperation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

public class Blockchain {
    private static  final LinkedList<Block> AsDb = new LinkedList<>();
    private static final String BLOCKCHAIN_FILE = "Data Block";

    public static  void  FileBlock(LinkedList<Block>chain) {

        try (
                FileOutputStream fileBlock = new FileOutputStream(BLOCKCHAIN_FILE);
                ObjectOutputStream out = new ObjectOutputStream(fileBlock);
        ) {
            out.writeObject(AsDb);
            System.out.println("File is Updated");
        } catch (IOException e) {
        } finally {

        }
    }
    public static LinkedList<Block>get(){
        try(
            FileInputStream fis = new FileInputStream(BLOCKCHAIN_FILE);
            ObjectInputStream in = new ObjectInputStream(fis);)
        {
                return (LinkedList<Block>)in.readObject();

        }catch (Exception e){
         return null;
        }

    }
    public  static  void distribute(String temp){
        try{

            Files.write(Paths.get(GeneralOperation.getBcd_path()), temp.getBytes(), StandardOpenOption.CREATE);

        }catch(Exception e){

        }
    }

}





