package bcd.config;

public class GeneralOperation {
    private static final String record_path = ".\\record\\blockchain\\ledger.txt";
    private static final String pvk_path = ".\\record\\key\\private_key\\private_key.txt";
    private static final String pbk_path = ".\\record\\key\\public_key\\public_key.txt";
    private static final String sha_Algo = "SHA-256";
    private static final String rsa_Algo = "RSA";
    private static final String sha_rsa_Algo = "SHA256WithRSA";
    private static final String xlsx_path = ".\\record\\xlsx\\student_record.xlsx";
    private static final String master_binary = "master/chain.bin";
    private static final String signature_path = "record/user/signature/signature.txt";
    public static String get_public_path(){
        return pbk_path;
    }
    public static String get_private_path(){
        return pvk_path;
    }
    public static String getSha_Algo(){
        return sha_Algo;
    }
    public static String getXlsx_path(){
        return xlsx_path;
    }
    public static String getRsa_Algo(){return rsa_Algo;}

    public static void saveKey(String path, byte[] encoded) {

    }
    public static String getRecord_path(){
        return record_path;
    }
    public static String getSha_rsa_Algo(){return sha_rsa_Algo;}
    public static String getMaster_binary(){return master_binary;}
    public static String get_signature_path(){return signature_path;}

}
