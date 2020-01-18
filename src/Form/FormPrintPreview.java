package Form;

import Library.Koneksi;
import Library.TempData;
import Library.TempUser;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormPrintPreview extends javax.swing.JDialog {

    private int x, y, total, bayar, kembali, qtyTotal;
    private String areaa;
    private final Koneksi con = new Koneksi();
    private final ArrayList<String> nama, harga, qty, sub, diskon;
            
    private String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy - hh:mm").format(new Date());
    }
    
    private String centerString (int width, String s) {
        return String.format("%-" + width  + "s %n", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
    
    private void getSource() {
        try {
            ResultSet rs = con.getQuery("SELECT * FROM VIEW_DETAIL_PENJUALAN WHERE "
                    + "kd_penjualan ='" + TempData.getIDBarang() + "'");
            while(rs.next()) {
                nama.add(rs.getString(3));
                harga.add(rs.getString(5));
                qty.add(rs.getString(4));
                sub.add(rs.getString(6));
                diskon.add(rs.getString(7));
                qtyTotal += rs.getInt(4);
            }
            
            rs = con.getQuery("SELECT * FROM PENJUALAN WHERE "
                    +  "kd_penjualan ='" + TempData.getIDBarang() + "'");
            if(rs.next()) {
                total = rs.getInt(4);
                bayar = rs.getInt(5);
                kembali = rs.getInt(6);
            }
        } catch (SQLException e) {
            System.err.println("Error getNama : " + e.getMessage());
        }
    }
    
    private String areaString() {
        areaa = "\n" + centerString(50, "UD. STIKI JAYA.") + 
               "" + centerString(50, "JL. Tidar, No.100  Malang") +
               "" + centerString(50, "Telp. 0332-7847283, Fax : ") + 
               "" + centerString(50, "MENYEDIAKAN SEGALA MACAM KEBUTUHAN") +
               "" + centerString(50, "SEHARI HARI ANDA") +
               "" + centerString(50, "-------------------------------------------") +
               "" + String.format("%-18s %27s", "   " + getDate(), TempData.getIDBarang() + "/" + TempUser.getUsername() + "/Kasir" + "  \n");
        
        areaa += centerString(50, "-------------------------------------------");
        
        for(int i = 0; i < nama.size(); i++) {
            areaa += String.format("%-43s%n", "   " + nama.get(i));
            
            areaa += String.format("%-15s%-15s%16s", 
                    "   " + harga.get(i),
                        "  x  " + qty.get(i), 
                            sub.get(i));
            if(!diskon.get(i).equals("0")) {
                
            }
            areaa += "\n";
        }
        
        areaa += centerString(50, "-------------------------------------------");
        areaa += String.format("%30s%16s%n", "TOTAL/QTY : Rp.", total + "/" + qtyTotal);
        areaa += String.format("%30s%16d%n", "BAYAR : Rp.", bayar);
        areaa += String.format("%30s%16d%n", "KEMBALI : Rp.", kembali);
        areaa += centerString(50, "-------------------------------------------");
        areaa += centerString(50, "TERIMAKASIH ATAS KUNJUNGAN ANDA");
        areaa += centerString(50, "BARANG YANG SUDAH DIBELI TIDAK DAPAT");
        areaa += centerString(50, "DITUKAR / DIKEMBALIKAN");
        
        return areaa;
    }
    
    
    
    
    
    public FormPrintPreview(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        nama = new ArrayList<>();
        harga = new ArrayList<>();
        qty = new ArrayList<>();
        sub = new ArrayList<>();
        diskon = new ArrayList<>();
        getSource();
        
        area.setText(areaString());
        if(!modal) {
            try {
                area.print();
            } catch (PrinterException ex) {
                Logger.getLogger(FormPrintPreview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        btnCetak = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FORM PRINT PREVIEW");

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        area.setEditable(false);
        area.setColumns(50);
        area.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        area.setRows(5);
        area.setTabSize(50);
        jScrollPane1.setViewportView(area);

        btnCetak.setBackground(new java.awt.Color(22, 160, 133));
        btnCetak.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(254, 254, 254));
        btnCetak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_print.png"))); // NOI18N
        btnCetak.setText("Cetak");
        btnCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCetak.setOpaque(true);
        btnCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onCetak(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(btnCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(518, 452));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void onCetak(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onCetak
        try {
            this.dispose();
            area.print();
        } catch (PrinterException ex) {
            Logger.getLogger(FormPrintPreview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_onCetak

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrintPreview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            FormPrintPreview dialog = new FormPrintPreview(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private static javax.swing.JLabel btnCetak;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
