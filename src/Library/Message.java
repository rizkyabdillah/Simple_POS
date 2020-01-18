package Library;

import javax.swing.JOptionPane;

public class Message {
    
    public Message() {
        
    }
    
    public void msgWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning Message!", JOptionPane.WARNING_MESSAGE);
    }
    
    public void msgInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information Message!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void msgPlain(String message) {
        JOptionPane.showMessageDialog(null, message, "Plain Message!", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void msgError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error Message!", JOptionPane.ERROR_MESSAGE);
    }
    
    public int msgQuestion(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirmation Message!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    
    
}
