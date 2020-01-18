package Form;

import Library.Koneksi;
import Library.LinkedList;
import Library.LinkedListNode;
import Library.Message;
import Library.TempData;
import Library.TempUser;
import Library.Transaksi;
import Library.Validasi;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FormTransaksi extends javax.swing.JDialog {

    private final Message msg = new Message();
    private final Koneksi con = new Koneksi();
    private final LinkedList link = new LinkedList();
    private final Validasi valid = new Validasi();
    private String qty = "", barcode = "", maxID = "";
    private int kembali = 0;
    private boolean edited = false;
    private ResultSet rs;

    public FormTransaksi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        showDate();
        lblID.setText("NO BILL : " + getMaxID());
        formatCurrTotal();
        System.out.println(tabel.getRowCount());
    }

    public final String currencyRpFormat(int value) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(value);
    }

    private String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    private String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private void showDate() {
        try {
            Thread tr = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            date.setText(getDate() + " -  " + getTime());
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            tr.start();
        } catch (Exception e) {
            msg.msgError("Error show date : " + e.getMessage());
        }
    }

    private void formatCurrTotal() {
        boxTotal.setText(currencyRpFormat(link.getTotal()));
    }

    private String getMaxID() {
        maxID = con.getAutoMaxID("324", 7, "PENJUALAN", "kd_penjualan");;
        return maxID;
    }
    
    private void save(boolean print) {
        try {
            if (link.getTotal() == 0) {
                msg.msgWarning("Belanjaan anda kosong!");
                boxKembali.setText("Rp. 0,00");
            } else if (boxBayar.getText().isEmpty()) {
                msg.msgWarning("Pembayaran anda belum terisi!");
                boxBayar.requestFocus();
            } else if (link.getTotal() > new Integer(boxBayar.getText())) {
                msg.msgWarning("Pembayaran anda kurang!");
                boxKembali.setText("Rp. 0,00");
            } else {
                kembali = new Integer(boxBayar.getText()) - link.getTotal();
                boxKembali.setText(currencyRpFormat(kembali));
                int j = msg.msgQuestion("Apakah anda yakin ingin menyimpan transaksi?");
                if (j == JOptionPane.YES_OPTION) {
                    con.setExecute("INSERT INTO PENJUALAN "
                            + "VALUES('" + maxID + "',"
                                + "'" + TempUser.getUserId() + "',"
                                    + "'" + getDate() + "',"
                                        + "'" + link.getTotal() + "',"
                                            + "'" + boxBayar.getText() + "',"
                                                + "'" + kembali + "')");
                    for (int i = 0; i < tabel.getRowCount(); i++) {
                        con.setExecute("INSERT INTO DETAIL_PENJUALAN "
                                + "VALUES('" + maxID + "',"
                                    + "'" + tabel.getValueAt(i, 0) + "',"
                                        + "'" + tabel.getValueAt(i, 2) + "',"
                                            + "'" + tabel.getValueAt(i, 4) + "')");
                    }
                    if(print) {
                        TempData.setIDBar(maxID);
                        new FormPrintPreview(null, true).setVisible(true);
                    }
                    link.popAll();
                    boxBayar.setText("");
                    boxKembali.setText("Rp. 0,00");
                    onCancel(null);
                }
            }
        } catch (Exception e) {
            msg.msgError("Error Simpan : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        boxTotal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        boxBarcode = new javax.swing.JTextField();
        boxQty = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        boxBayar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        boxKembali = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Transaksi");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                onOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        tabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BARCODE", "NAMA BARANG", "JUMLAH", "HARGA", "SUB TOTAL", "DISKON"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel.setRowHeight(35);
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onPilihTabel(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);
        if (tabel.getColumnModel().getColumnCount() > 0) {
            tabel.getColumnModel().getColumn(0).setResizable(false);
            tabel.getColumnModel().getColumn(0).setPreferredWidth(120);
            tabel.getColumnModel().getColumn(1).setResizable(false);
            tabel.getColumnModel().getColumn(1).setPreferredWidth(500);
            tabel.getColumnModel().getColumn(2).setResizable(false);
            tabel.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabel.getColumnModel().getColumn(3).setResizable(false);
            tabel.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabel.getColumnModel().getColumn(4).setResizable(false);
            tabel.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabel.getColumnModel().getColumn(5).setResizable(false);
            tabel.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        boxTotal.setEditable(false);
        boxTotal.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        boxTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        boxTotal.setText("Rp. 0");

        jPanel4.setBackground(new java.awt.Color(22, 160, 133));

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(254, 254, 254));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_transaksi_120.png"))); // NOI18N
        jLabel6.setText("FORM TRANSAKSI");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel1.setText("Barcode ");

        boxBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBarcode(evt);
            }
        });
        boxBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                onFocusBayar(evt);
            }
        });

        boxQty.setEditable(false);
        boxQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onQty(evt);
            }
        });
        boxQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                onValidQty(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(22, 160, 133));
        jLabel4.setForeground(new java.awt.Color(254, 254, 254));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_close.png"))); // NOI18N
        jLabel4.setText("Cancel");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onCancel(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(22, 160, 133));
        jLabel5.setForeground(new java.awt.Color(254, 254, 254));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_edit.png"))); // NOI18N
        jLabel5.setText("Edit");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onEdited(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(22, 160, 133));
        jLabel7.setForeground(new java.awt.Color(254, 254, 254));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_hapus.png"))); // NOI18N
        jLabel7.setText("Delete");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onDelete(evt);
            }
        });

        lblID.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        lblID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblID.setText("- - - - -");

        jLabel8.setBackground(new java.awt.Color(22, 160, 133));
        jLabel8.setForeground(new java.awt.Color(254, 254, 254));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_print.png"))); // NOI18N
        jLabel8.setText("SAVE & PRINT");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onPrint(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(22, 160, 133));
        jLabel9.setForeground(new java.awt.Color(254, 254, 254));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/a_simpan.png"))); // NOI18N
        jLabel9.setText("SAVE");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.setOpaque(true);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onSimpan(evt);
            }
        });

        boxBayar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        boxBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBayar(evt);
            }
        });
        boxBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                onValidBayar(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel3.setText("Bayar");

        boxKembali.setEditable(false);
        boxKembali.setFont(new java.awt.Font("Lohit Devanagari", 1, 13)); // NOI18N
        boxKembali.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        boxKembali.setText("Rp. 0,00");

        jLabel10.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        jLabel10.setText("Kembali");

        date.setFont(new java.awt.Font("Lohit Devanagari", 0, 14)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("JAM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(boxBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(boxKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addComponent(boxBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(boxQty, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(boxTotal))
                .addGap(3, 3, 3))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(boxTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boxBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boxBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        setSize(new java.awt.Dimension(1050, 542));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void onOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onOpened
        boxBarcode.requestFocus();
    }//GEN-LAST:event_onOpened

    private void onBarcode(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBarcode
        try {
            rs = con.getQuery("SELECT * FROM BARANG WHERE barcode ='" + boxBarcode.getText() + "'");
            if (rs.next()) {
                boxTotal.setText(rs.getString(2));
                boxQty.setEditable(true);
                boxQty.requestFocus();
                boxBarcode.setEditable(false);
            } else {
                boxQty.setText("");
                boxQty.setEditable(false);
                msg.msgWarning("Barcode tidak terdaftar");
            }
        } catch (SQLException e) {
            msg.msgError("Error : " + e.getMessage());
        }
    }//GEN-LAST:event_onBarcode

    private void onCancel(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onCancel
        formatCurrTotal();
        boxBarcode.requestFocus();
        boxQty.setText("");
        boxBarcode.setText("");
        link.showTable(tabel);
        boxQty.setEditable(false);
        boxBarcode.setEditable(true);
        barcode = "";
        lblID.setText("NO BILL : " + getMaxID());
        edited = false;
    }//GEN-LAST:event_onCancel

    private void onValidQty(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onValidQty
        if (boxQty.getText().length() > 4) {
            msg.msgWarning("Pembelian terlalu banyak!");
            evt.consume();
        } else {
            valid.validInt(evt);
        }
    }//GEN-LAST:event_onValidQty

    private void onQty(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onQty
        try {
            if (boxQty.getText().isEmpty()) {
                msg.msgWarning("Kolom quantity kosong!");
            } else if (boxQty.getText().equals("0")) {
                msg.msgWarning("Pembelian tidak boleh nol!");
                boxQty.setText("");
                boxQty.requestFocus();
            } else {
                rs = con.getQuery("SELECT * FROM BARANG WHERE barcode ='" + boxBarcode.getText() + "'");
                if (rs.next()) {
                    int sub = rs.getInt(3) * new Integer(boxQty.getText());
                    int stok = rs.getInt("stok");
                    if (!edited) {
                        if (stok < (link.getQty(boxBarcode.getText()) + new Integer(boxQty.getText()))) {
                            msg.msgWarning("Pembelian melebihi stok!");
                        } else {
                            if (!link.filterBarang(boxBarcode.getText(), new Integer(boxQty.getText()), sub)) {
                                Transaksi t = new Transaksi();
                                t.addBarcode(rs.getString(1));
                                t.addNama(rs.getString(2));
                                t.addJumlah(new Integer(boxQty.getText()));
                                t.addHarga(rs.getInt(3));
                                t.addSubTotal(sub);
                                t.addDiskon(rs.getInt(8));
                                link.push(new LinkedListNode(t));
                            }
                        }
                    } else {
                        if (stok < new Integer(boxQty.getText())) {
                            msg.msgWarning("Pembelian melebihi stok!");
                        } else {
                            link.editBarang(boxBarcode.getText(), new Integer(boxQty.getText()), sub);
                        }
                    }
                    edited = false;
                    link.showTable(tabel);
                    onCancel(null);
                }
            }
        } catch (Exception e) {
            msg.msgError("Error qty : " + e.getMessage());
        }
    }//GEN-LAST:event_onQty

    private void onEdited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onEdited
        try {
            if (barcode.equals("")) {
                msg.msgWarning("Mohon pilih data yang akan diubah!");
            } else {
                boxQty.setEditable(true);
                boxQty.requestFocus();
                boxBarcode.setEditable(false);
                boxBarcode.setText(barcode);
                boxQty.setText(qty);
                edited = true;
            }
        } catch (Exception e) {
            msg.msgError("Error edited : " + e.getMessage());
        }
    }//GEN-LAST:event_onEdited

    private void onPilihTabel(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onPilihTabel
        try {
            barcode = tabel.getValueAt(tabel.getSelectedRow(), 0).toString();
            qty = tabel.getValueAt(tabel.getSelectedRow(), 2).toString();
        } catch (Exception e) {
            msg.msgError("Error pilih tabel : " + e.getMessage());
        }
    }//GEN-LAST:event_onPilihTabel

    private void onDelete(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onDelete
        try {
            if (barcode.equals("")) {
                msg.msgWarning("Mohon pilih data yang akan dihapus!");
            } else {
                int i = msg.msgQuestion("Apakah anda yakin ingin menghapus?");
                if (i == JOptionPane.YES_OPTION) {
                    link.deleteBarang(barcode);
                }
                onCancel(null);
            }
        } catch (Exception e) {
            msg.msgWarning("Error deleted : " + e.getMessage());
        }
    }//GEN-LAST:event_onDelete

    private void onFocusBayar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onFocusBayar
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ESCAPE) {
            boxBayar.requestFocus();
        }
    }//GEN-LAST:event_onFocusBayar

    private void onBayar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBayar
        try {
            if (boxBayar.getText().isEmpty()) {
                msg.msgWarning("Pembayaran anda belum terisi!");
            } else if (link.getTotal() == 0) {
                msg.msgWarning("Belanjaan anda kosong!");
                boxKembali.setText("Rp. 0,00");
            } else if (link.getTotal() > new Integer(boxBayar.getText())) {
                msg.msgWarning("Pembayaran anda kurang!");
                boxKembali.setText("Rp. 0,00");
            } else {
                kembali = new Integer(boxBayar.getText()) - link.getTotal();
                boxKembali.setText(currencyRpFormat(kembali));
            }
        } catch (Exception e) {
            msg.msgWarning("Error Bayar : " + e.getMessage());
        }
    }//GEN-LAST:event_onBayar

    private void onValidBayar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onValidBayar
        valid.validInt(evt);
    }//GEN-LAST:event_onValidBayar
 
    private void onSimpan(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onSimpan
        save(false);
    }//GEN-LAST:event_onSimpan

    private void onPrint(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onPrint
        save(true);
    }//GEN-LAST:event_onPrint

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            FormTransaksi dialog = new FormTransaksi(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField boxBarcode;
    private javax.swing.JTextField boxBayar;
    private javax.swing.JTextField boxKembali;
    private javax.swing.JTextField boxQty;
    private javax.swing.JTextField boxTotal;
    private javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JTable tabel;
    // End of variables declaration//GEN-END:variables
}
