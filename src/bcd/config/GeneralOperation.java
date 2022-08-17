package bcd.config;

public class GeneralOperation {
    private static final String pvk_path = ".\\record\\key\\private_key\\private_key.txt";
    private static final String pbk_path = ".\\record\\key\\public_key\\public_key.txt";
    private static final String Algorithm = "havent decided yet";
    private static final String xlsx_path = ".\\record\\xlsx\\student_record.xlsx";
    public static String get_public_path(){
        return pbk_path;
    }
    public static String get_private_path(){
        return pbk_path;
    }
    public static void write_file(byte[] codes, String path){

    }
    public static String getAlgorithm(){
        return Algorithm;
    }
    public static String getXlsx_path(){
        return xlsx_path;
    }

    public static void saveKey(String path, byte[] encoded) {

    }
}
