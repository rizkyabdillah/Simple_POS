package Library;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Koneksi {
    
    private Message msg = new Message();
    
    private Connection con;
    private Statement st;
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE = "Penjualan";
    private final String USER = "root";
    private final String PASS = "";
    
    public Koneksi() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE, USER, PASS);
            st = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            msg.msgError("Error Koneksi : " + e.getMessage());
        }
    }
    
    public ResultSet getQuery(String query) {
        try {
            return st.executeQuery(query);
        } catch (SQLException e) {
            msg.msgError("Error Query : " + e.getMessage());
            return null;
        }
    }
    
    public void setExecute(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            msg.msgError("Error Execute : " + e.getMessage());
        }
    }
        
    public void showTabel(JTable dgrv1, String[] header, String sql) {
        try {
            DefaultTableModel model = new DefaultTableModel(header, 1) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
                        
            TableCellRenderer render = dgrv1.getTableHeader().getDefaultRenderer();
            JLabel headerlbl = (JLabel) render;
            headerlbl.setHorizontalAlignment(JLabel.CENTER);
            headerlbl.setFont(new java.awt.Font("Lohit Devanagari", 0, 12));
            
            dgrv1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

                DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                    Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (isSelected) {
                        c.setBackground(LibColor.getHrefColor());
                        c.setForeground(Color.WHITE);
                    } else if (row % 2 == 0) {
                        c.setBackground(LibColor.getColor());
                        c.setForeground(Color.WHITE);
                    } else {
                        c.setBackground(LibColor.foreColor());
                        c.setForeground(Color.WHITE);
                    }
                    return c;
                }
            });
            dgrv1.setRowHeight(38);
            
            ResultSet rs = getQuery(sql);

            int baris = 0;
            while (rs.next()) {
                String[] kolom = new String[header.length];
                for (int i = 0; i < header.length; i++) {
                    kolom[i] = rs.getString(i + 1);
                }
                model.insertRow(baris, kolom);
                baris++;
            }

            dgrv1.setModel(model);
            
        } catch (Exception e) {
            msg.msgError("Error Show Tabel : " + e.getMessage());
        }
    }
    
    public String getAutoMaxID(String prefix, int length, String table, String column) {
        String id = null;
        try {
            ResultSet rs = getQuery("SELECT MAX(SUBSTRING(" 
                    + column + ", " + (prefix.length() + 1) + ", " + length + ")) "
                        + "FROM " + table + "");
            if(rs.next()) {
                id = prefix + String.format("%0" + (length - prefix.length()) + "d", rs.getInt(1) + 1);
            } else {
                id = prefix + String.format("%0" + (length - prefix.length()) + "d", 1);
            }
        } catch (Exception e) {
            msg.msgError("Error getAutoMaxID : " + e.getMessage());
        }
        return id;
    }
    
}
