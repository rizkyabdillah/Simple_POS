package Form;

import Library.Koneksi;
import Library.Message;
import Library.TempData;
import Library.Validasi;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FormBarangAdd extends javax.swing.JDialog {
    
    private final Koneksi con = new Koneksi();
    private final Message msg = new Message();
    private final Validasi valid = new Validasi();
    private int x, y;
    private ArrayList<String>  satuan = new ArrayList<>();
    private ArrayList<String>  kd_kategori = new ArrayList<>();
    private ResultSet rs;

    public FormBarangAdd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        status();
             
    }
    
    private void status() {
        try {
            rs = con.getQuery("SELECT * FROM KATEGORI_BARANG");
            while(rs.next()) {
                boxKategori.addItem(rs.getString("nama_kategori"));
                satuan.add(rs.getString("jenis_satuan"));
                kd_kategori.add(rs.getString("kd_kategori"));
            }
            
            if(TempData.getStatus()) {
                btnSimpan.setText("Ubah");
                rs = con.getQuery("SELECT * FROM VIEW_BARANG WHERE barcode ='" + TempData.getIDBarang()+ "'");
                if(rs.next()) {         
                    boxBarcode.setEditable(false);
                    boxHargaJual.setEditable(true);
                    boxBarcode.setText(rs.getString("barcode"));
                    boxNama.setText(rs.getString("nama_barang"));
                    boxHargaJual.setText(rs.getString("harga_jual"));
                    boxHargaBeli.setText(rs.getString("harga_beli"));
                    boxKategori.setSelectedItem(rs.getString("nama_kategori"));
                    boxNetto.setText(rs.getString("netto").substring(0, rs.getString("netto").length() - 3));
                    boxStok.setText(rs.getString("stok"));
                    boxDiskon.setText(rs.getString("diskon").substring(0, rs.getString("diskon").length() - 1));
                }
            }
        } catch (Exception e) {
            msg.msgError("Error Status : " + e.getMessage());
        }
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupCB = new javax.swing.ButtonGroup();
        BG = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        boxBarcode = new javax.swing.JTextField();
        boxNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        boxHargaBeli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JLabel();
        boxStok = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        boxKategori = new javax.swing.JComboBox<>();
        boxHargaJual = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        boxNetto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        posFix = new javax.swing.JLabel();
        boxDiskon = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Add Barang");
        setBackground(new java.awt.Color(254, 254, 254));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                onActiv(evt);
            }
        });

        BG.setBackground(new java.awt.Color(254, 254, 254));

        jLabel2.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel2.setText("Barcode");

        boxBarcode.setFont(new java.awt.Font("Lohit Devanagari", 0, 12)); // NOI18N
        boxBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                onValidBarcode(evt);
            }
        });

        boxNama.setFont(new java.awt.Font("Lohit Devanagari", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel3.setText("Nama Barang");

        boxHargaBeli.setFont(new java.awt.Font("Lohit Devanagari", 0, 12)); // NOI18N
        boxHargaBeli.setToolTipText("Harus Angka");
        boxHargaBeli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validHargaBeli(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cekKosong(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel5.setText("Harga Beli");

        jLabel7.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel7.setText("Kategori");

        jLabel8.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel8.setText("Diskon");

        btnSimpan.setBackground(new java.awt.Color(22, 160, 133));
        btnSimpan.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(254, 254, 254));
        btnSimpan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_simpan.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setOpaque(true);
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onSimpan(evt);
            }
        });

        boxStok.setFont(new java.awt.Font("Lohit Devanagari", 0, 12)); // NOI18N
        boxStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                onValidStok(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel9.setText("Stok");

        boxKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Kategori" }));
        boxKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boxKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onPilihKategori(evt);
            }
        });

        boxHargaJual.setEditable(false);
        boxHargaJual.setFont(new java.awt.Font("Lohit Devanagari", 0, 12)); // NOI18N
        boxHargaJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                validHargaJual(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel10.setText("Harga Jual");

        boxNetto.setFont(new java.awt.Font("Lohit Devanagari", 0, 12)); // NOI18N
        boxNetto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                onValidNetto(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel11.setText("Netto");

        posFix.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        posFix.setText("??");

        boxDiskon.setFont(new java.awt.Font("Lohit Devanagari", 0, 12)); // NOI18N
        boxDiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                onValidDiskon(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(22, 160, 133));

        jLabel14.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(254, 254, 254));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_barang_120.png"))); // NOI18N
        jLabel14.setText("FORM BARANG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout BGLayout = new javax.swing.GroupLayout(BG);
        BG.setLayout(BGLayout);
        BGLayout.setHorizontalGroup(
            BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BGLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxBarcode))
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxNama))
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxHargaBeli))
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxStok))
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxKategori, 0, 380, Short.MAX_VALUE))
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxHargaJual))
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxNetto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(posFix, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BGLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxDiskon)))
                .addGap(12, 12, 12))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BGLayout.setVerticalGroup(
            BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BGLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxHargaJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxNetto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posFix, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(BGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(541, 563));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void onActiv(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onActiv
        this.setAlwaysOnTop(true);
    }//GEN-LAST:event_onActiv

    private void onSimpan(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onSimpan
        this.setAlwaysOnTop(false);
        try {
            if(boxBarcode.getText().isEmpty()) {
                msg.msgWarning("Kolom barcode tidak boleh kosong!");
                boxBarcode.requestFocus();
            } else if(boxNama.getText().isEmpty()) {
                msg.msgWarning("Kolom nama tidak boleh kosong!");
                boxNama.requestFocus();
            } else if(boxHargaBeli.getText().isEmpty()) {
                msg.msgWarning("Kolom harga jual tidak boleh kosong!");
                boxHargaBeli.requestFocus();
            } else if(boxHargaJual.getText().isEmpty()) {
                msg.msgWarning("Kolom harga beli tidak boleh kosong!");
                boxHargaJual.requestFocus();
            } else if(boxKategori.getSelectedIndex() == 0) {
                msg.msgWarning("Mohon pilih kategori!");
                boxKategori.requestFocus();
            } else if(boxNetto.getText().isEmpty()) {
                msg.msgWarning("Kolom netto tidak boleh kosong!");
                boxNetto.requestFocus();
            } else if(boxStok.getText().isEmpty()) {
                msg.msgWarning("Kolom stok tidak boleh kosong!");
                boxStok.requestFocus();
            } else if(boxDiskon.getText().isEmpty()) {
                msg.msgWarning("Kolom diskon tidak boleh kosong!");
                boxDiskon.requestFocus();
            } else if(Integer.parseInt(boxHargaJual.getText()) <= Integer.parseInt(boxHargaBeli.getText())) {
                msg.msgWarning("Harga jual tidak boleh sama atau lebih kecil dari harga beli!");
                boxHargaJual.setText("");
                boxHargaJual.requestFocus();
            } else if(boxHargaBeli.getText().equals("0")) {
                msg.msgWarning("Harga beli tidak boleh bernilai 0 (nol)!");
                boxHargaBeli.setText("");
                boxHargaBeli.requestFocus();
            } else if(boxNetto.getText().equals("0")) {
                msg.msgWarning("Isi netto tidak boleh bernilai 0 (nol)!");
                boxNetto.setText("");
                boxNetto.requestFocus();
            } else {
                if(TempData.getStatus()) {
                    con.setExecute("UPDATE BARANG SET "
                        + "nama_barang ='" + boxNama.getText() + "', "
                            + "harga_jual ='" + boxHargaJual.getText() + "', "
                                + "harga_beli ='" + boxHargaBeli.getText() + "', "
                                    + "netto ='" + boxNetto.getText() + "', "
                                        + "kd_kategori ='" + kd_kategori.get(boxKategori.getSelectedIndex() - 1) + "', "
                                            + "stok ='" + boxStok.getText() + "', "
                                                + "diskon ='" + boxDiskon.getText() + "' "
                                                    + "WHERE barcode ='" + boxBarcode.getText() + "'");
                } else {
                    con.setExecute("INSERT INTO BARANG SET "
                        + "nama_barang ='" + boxNama.getText() + "', "
                            + "harga_jual ='" + boxHargaJual.getText() + "', "
                                + "harga_beli ='" + boxHargaBeli.getText() + "', "
                                    + "netto ='" + boxNetto.getText() + "', "
                                        + "kd_kategori ='" + kd_kategori.get(boxKategori.getSelectedIndex() - 1) + "', "
                                            + "stok ='" + boxStok.getText() + "', "
                                                + "diskon ='" + boxDiskon.getText() + "', "
                                                    + "barcode ='" + boxBarcode.getText() + "'");
                }
                dispose();
            }
        } catch (Exception e) {
            msg.msgError("Error Simpan : ");
        }
    }//GEN-LAST:event_onSimpan

    private void onPilihKategori(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onPilihKategori
        if(boxKategori.getSelectedIndex() == 0) {
            posFix.setText("??");
        } else {
            posFix.setText(satuan.get(boxKategori.getSelectedIndex() - 1));
        }
    }//GEN-LAST:event_onPilihKategori

    private void onValidBarcode(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onValidBarcode
        if(boxBarcode.getText().length() > 15) {
            msg.msgWarning("Panjang barcode tidak boleh lebih dari 16 angka");
            evt.consume();
        } else {
            valid.validInt(evt);
        }
    }//GEN-LAST:event_onValidBarcode

    private void validHargaBeli(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_validHargaBeli
        if(boxHargaBeli.getText().length() > 9) {
            msg.msgWarning("Harga beli tidak boleh melebihi dari Rp. 9.999.999.999!");
            evt.consume();
        } else {
            valid.validInt(evt);
        }
        
    }//GEN-LAST:event_validHargaBeli

    private void validHargaJual(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_validHargaJual
        if(boxHargaJual.getText().length() > 9) {
            msg.msgWarning("Harga jual tidak boleh melebihi dari Rp. 9.999.999.999!");
            evt.consume();
        } else {
            valid.validInt(evt);
        }
        
    }//GEN-LAST:event_validHargaJual

    private void cekKosong(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKosong
        if(boxHargaBeli.getText().isEmpty()) {
            boxHargaJual.setEditable(false);
            boxHargaJual.setText("");
        } else {
            boxHargaJual.setEditable(true);
        }
    }//GEN-LAST:event_cekKosong

    private void onValidNetto(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onValidNetto
        if(boxNetto.getText().length() > 5) {
            msg.msgWarning("Netto tidak boleh melebihi dari 999.999!");
            evt.consume();
        } else {
            valid.validInt(evt);
        }
    }//GEN-LAST:event_onValidNetto

    private void onValidDiskon(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onValidDiskon
        if(boxDiskon.getText().length() > 1) {
            msg.msgWarning("Diskon maksimal adalah 99%!");
            evt.consume();
        } else {
            valid.validInt(evt);
        }
    }//GEN-LAST:event_onValidDiskon

    private void onValidStok(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onValidStok
        if(boxStok.getText().length() > 3) {
            msg.msgWarning("Diskon maksimal adalah 9999!");
            evt.consume();
        } else {
            valid.validInt(evt);
        }
    }//GEN-LAST:event_onValidStok

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarangAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            FormBarangAdd dialog = new FormBarangAdd(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel BG;
    private javax.swing.JTextField boxBarcode;
    private javax.swing.JTextField boxDiskon;
    private javax.swing.JTextField boxHargaBeli;
    private javax.swing.JTextField boxHargaJual;
    private javax.swing.JComboBox<String> boxKategori;
    private javax.swing.JTextField boxNama;
    private javax.swing.JTextField boxNetto;
    private javax.swing.JTextField boxStok;
    private static javax.swing.JLabel btnSimpan;
    private javax.swing.ButtonGroup groupCB;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel posFix;
    // End of variables declaration//GEN-END:variables
}
