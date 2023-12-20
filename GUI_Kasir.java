/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ProjectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wisan
 */
public class GUI_Kasir extends javax.swing.JFrame {

    String Judul1, Penulis1, Penerbit1, search, Harga1, JumlahBeli1, TotalHarga1, TahunTerbit1;
    String ID1;

    /**
     * Creates new form Kasir
     */
    public GUI_Kasir() {
        initComponents();
        tampil();
    }

    public void itemTerpilih() {
        PenjualanBuku pj = new PenjualanBuku();
        txtJudul.setText(Judul1);
        txtPenulis.setText(Penulis1);
        txtPenerbit.setText(Penerbit1);
        txtHarga.setText(Harga1);
        txtTerbit.setText(TahunTerbit1);
        txtJmlhBeli.setText(JumlahBeli1);
        txtTotalH.setText(TotalHarga1);
    }

    public Connection con;

    public void koneksi() throws SQLException {
        try {
            con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/OOP_2218095?user=root&password=");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(GUI_Kasir.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Kasir.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Kasir.class.getName()).log(Level.SEVERE, null, es);
        }
    }

    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("ID");
        tabelhead.addColumn("Judul");
        tabelhead.addColumn("Penulis");
        tabelhead.addColumn("Penerbit");
        tabelhead.addColumn("Tahun Terbit");
        tabelhead.addColumn("Harga");
        tabelhead.addColumn("Jumlah Beli");
        tabelhead.addColumn("Total Harga");
        try {
            koneksi();
            String sql = "Select * from kasir";
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString("ID"), res.getString("judul"), res.getString("penulis"), res.getString("penerbit"), res.getString("tahun terbit"), res.getString("harga"), res.getString("jumlah beli"), res.getString("total harga")});
            }
            TabelKasir.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Memuat Data");
        }
    }

    public void insert() {
        try {
            koneksi();
            Statement st = con.createStatement();
            Judul1 = txtJudul.getText();
            Penulis1 = txtPenulis.getText();
            Penerbit1 = txtPenerbit.getText();
            TahunTerbit1 = txtTerbit.getText();
            Harga1 = txtHarga.getText();
            JumlahBeli1 = txtJmlhBeli.getText();
            TotalHarga1 = txtTotalH.getText();
            String sql = "INSERT INTO kasir (`Judul`, `Penulis`, `Penerbit`, `Tahun Terbit`, `Harga`,`Jumlah Beli`,`Total Harga`) VALUES "
                    + "('" + Judul1 + "','" + Penulis1 + "','" + Penerbit1 + "','" + TahunTerbit1 + "','" + Harga1 + "','" + JumlahBeli1 + "','" + TotalHarga1 + "')";
            st.executeUpdate(sql);
            st.close();
            tampil();
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void update() {
        try {
            koneksi();
            PenjualanBuku sb = new PenjualanBuku();
            Statement stat = con.createStatement();
            Judul1 = txtJudul.getText();
            Penulis1 = txtPenulis   .getText();
            Penerbit1 = txtPenerbit.getText();
            TahunTerbit1 = txtTerbit.getText();
            Harga1 = txtHarga.getText();
            JumlahBeli1 = txtJmlhBeli.getText();
            TotalHarga1 = txtTotalH.getText();
            String sql = "UPDATE kasir SET Judul='" + Judul1 + "',Penulis='" + Penulis1 + "',Penerbit='" + Penerbit1 + "',`Tahun Terbit`='" + TahunTerbit1 + "',Harga='" + Harga1 + "', `Jumlah Beli`= '" + JumlahBeli1 + "', `Total Harga`='"+TotalHarga1+"' WHERE ID ='" + ID1 + "'";
            stat.executeUpdate(sql);
            stat.close();
            tampil();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diperbarui");
            clear();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void hapus() {
        int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            try {
                koneksi();
                Statement st = con.createStatement();
                String query = "DELETE FROM kasir where ID = '" + ID1 + "'";
                st.executeUpdate(query);
                st.close();
                tampil();
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal Menghapus Data");
            }
        }
    }

    public void cari() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("ID");
        tabelhead.addColumn("Judul");
        tabelhead.addColumn("Penulis");
        tabelhead.addColumn("Penerbit");
        tabelhead.addColumn("Tahun Terbit");
        tabelhead.addColumn("Jumlah Beli");
        tabelhead.addColumn("Total Harga");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_penjualan where NamaProduk LIKE '%" + search + "%' OR Harga LIKE '%" + search + "%' OR NamaPembeli='" + search + "%' OR StokBarang='" + search + "%' OR Garansi='" + search + "'";
            Statement stat = con.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString("ID"), res.getString("TotalBarang"), res.getString("BarangTerbeli"), res.getString("NamaProduk"), res.getString("StockTersisa")});

            }
            TabelKasir.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void clear() {
        txtJudul.setText("");
        txtJmlhBeli.setText("");
        txtHarga.setText("");
        txtPenerbit.setText("");
        txtPenulis.setText("");
        txtTerbit.setText("");
        txtTotalH.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        txtTerbit = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtJmlhBeli = new javax.swing.JTextField();
        txtTotalH = new javax.swing.JTextField();
        btnCetak = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelKasir = new javax.swing.JTable();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("PEMBAYARAN KASIR");

        jLabel2.setText("JUDUL");

        jLabel3.setText("PENULIS");

        jLabel4.setText("PENERBIT");

        jLabel5.setText("TAHUN TERBIT");

        jLabel6.setText("HARGA");

        jLabel7.setText("JUMLAH BELI");

        jLabel8.setText("TOTAL HARGA");

        txtTerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTerbitActionPerformed(evt);
            }
        });

        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCetak.setText("CETAK");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        TabelKasir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Judul", "Penulis", "Penerbit", "Tahun Terbit", "Harga", "Jumlah Beli", "Total Harga"
            }
        ));
        TabelKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelKasirMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelKasir);

        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtJudul)
                    .addComponent(txtPenulis)
                    .addComponent(txtPenerbit)
                    .addComponent(txtTerbit)
                    .addComponent(txtHarga)
                    .addComponent(txtJmlhBeli)
                    .addComponent(txtTotalH)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(13, 13, 13)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCetak, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addGap(41, 41, 41)
                                .addComponent(btnBatal)
                                .addGap(42, 42, 42)
                                .addComponent(btnHapus)
                                .addGap(35, 35, 35)
                                .addComponent(btnClose)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtJmlhBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotalH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCetak)
                    .addComponent(btnClose)
                    .addComponent(btnBatal)
                    .addComponent(btnHapus))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTerbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTerbitActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        int total = Integer.parseInt(txtJmlhBeli.getText()) * Integer.parseInt(txtHarga.getText());
        txtTotalH.setText(String.valueOf(total));
        insert();
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_txtcariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int total = Integer.parseInt(txtJmlhBeli.getText()) * Integer.parseInt(txtHarga.getText());
        txtTotalH.setText(String.valueOf(total));
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TabelKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelKasirMouseClicked
        // TODO add your handling code here:
        int tabel = TabelKasir.getSelectedRow();

//      //System.out.println(TabelKasir.getValueAt(tabel, 10));
        ID1 = TabelKasir.getValueAt(tabel, 0).toString();
        Judul1 = TabelKasir.getValueAt(tabel, 1).toString();
        Penulis1 = TabelKasir.getValueAt(tabel, 2).toString();
        Penerbit1 = TabelKasir.getValueAt(tabel, 3).toString();
        TahunTerbit1 = TabelKasir.getValueAt(tabel, 4).toString();
        Harga1 = TabelKasir.getValueAt(tabel, 5).toString();
        JumlahBeli1 = TabelKasir.getValueAt(tabel, 6).toString();
        TotalHarga1 = TabelKasir.getValueAt(tabel, 7).toString();
        itemTerpilih();
    }//GEN-LAST:event_TabelKasirMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelKasir;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJmlhBeli;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtTerbit;
    private javax.swing.JTextField txtTotalH;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
