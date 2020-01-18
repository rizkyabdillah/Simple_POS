package Library;

public class TempData {
    
    private static String idkar, idbar, idsup, banyak;
    private static boolean status = false, trans = false;
    
    public TempData() {
        
    }
    
    public static void setStatus(boolean sts) {
        status = sts;
    }
    
    public static boolean getStatus() {
        return status;
    }
    
    
    
    public static void setIDKaryawan(String id) {
        idkar = id;
    }
    
    public static String getIdKar() {
        return idkar;
    }
    
    
    
    public static void setIDSuplier(String id) {
        idsup = id;
    }
    
    public static String getIDSuplier() {
        return idsup;
    }
    
    
    
    public static void setIDBar(String id) {
        idbar = id;
    }
    
    public static String getIDBarang() {
        return idbar;
    }
    
    
    
    public static void setBanyakBrg(String byk) {
        banyak = byk;
    }
    
    public static String getBanyakBrg() {
        return banyak;
    }
    
    
    
    public static void setTrans(boolean tr) {
        trans = tr;
    }
    
    public static boolean getTrans() {
        return trans;
    }
    
}
