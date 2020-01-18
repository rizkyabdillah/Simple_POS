package Library;

public class TempUser {
    
    private static String userId, username, password, jabatan;
    private static boolean status = false;

    public TempUser() {
        
    }
    
    public TempUser(String id, String user, String pass, String jbtn) {
        userId = id;
        username = user;
        password = pass;
        jabatan = jbtn;
    }
    
    public static void setUserId(String id) {
        userId = id;
    }
    
    public static void setUsername(String user) {
        username = user;
    }
    
    public static void setPassword(String pass) {
        password = pass;
    }
    
    public static void setJabatan(String jbtn) {
        jabatan = jbtn;
    }
    
    public static void setStatus(boolean sts) {
        status = sts;
    }
    
    
    
    
    
    
    public static String getUserId() {
        return userId;
    }
    
    public static String getUsername() {
        return username;
    }
    
    public static String getPassword() {
        return password;
    }
    
    public static String getJabatan() {
        return jabatan;
    }
    
    public static boolean getStatus() {
        return status;
    }
    
}
