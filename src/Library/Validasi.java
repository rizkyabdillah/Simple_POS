package Library;

import java.awt.event.KeyEvent;

public class Validasi {
    
    private final Message msg = new Message();
    
    public Validasi() {
        
    }
    
    public void validInt(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_ENTER)) {
            msg.msgWarning("Input harus berupa angka!");
            evt.consume();
        }
    }
    
    public void validChar(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (((c >= '0') && (c <= '9'))) {
            msg.msgWarning("Hanya boleh diisi huruf saja!");
            evt.consume();
        }
    }
    
    
}
