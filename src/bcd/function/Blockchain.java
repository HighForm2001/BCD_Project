package bcd.function;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class Blockchain {
    private static  final LinkedList<Block> AsDb = new LinkedList<>();
    private static final String BLOCKCHAIN_FILE = "Data Block";

    public static  void  FileBlock(LinkedList<Block>chain)  {

        try (
            FileOutputStream fileBlock = new FileOutputStream(BLOCKCHAIN_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileBlock);
            )
        {
            out.writeObject(AsDb);
            System.out.println("File is Updated");
        }catch(IOException e){}
        finally{

        }




    }




}
