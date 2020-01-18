package Library;

import java.awt.Color;

public class LibColor {
    
    private static Color color = new Color(22,160,133);
    private static Color foreColor = new Color(26, 188, 156);
    
    public static void setColor(Color col) {
        color = col;
    }
    
    public static Color getColor() {
        return color;
    }
    
    public static Color getHrefColor() {
        return new Color(color.getRGB() + 50);
    }
    
    public static Color foreColor() {
        return foreColor;
    }
    
}
