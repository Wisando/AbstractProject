/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectDatabase;

/**
 *
 * @author Wisan
 */
 public class PenjualanBuku extends Buku {
    private int jumlahBeli;
    private double totalHarga;
    
    // constructor
    public PenjualanBuku() {
        super();
        this.jumlahBeli = 0;
        this.totalHarga = 0;
    }
    // getter dan setter
    public int getJumlahBeli() {
        return jumlahBeli;
    }
    
   public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }
    
    public double getTotalHarga() {
        return totalHarga;
    }
    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }
     public void hitungTotalHarga() {
        try {
            // Check if harga is non-negative before calculating totalHarga
            if (super.getHarga() < 0 || jumlahBeli < 0) {
                throw new IllegalArgumentException("Harga dan Jumlah Beli Tidak Boleh Negatif");
            }
            this.totalHarga = this.jumlahBeli * super.getHarga();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
    }
    }
     public boolean cekBayar(String Harga, String jumlahBeli) {
        // Assuming getHarga() and getJumlahBeli() return strings
        return Harga.equals(super.getHarga()) && jumlahBeli.equals(String.valueOf(getJumlahBeli()));
    }
    // method untuk mencetak struk penjualan buku
    public void cetakStruk() {
        System.out.println("=====================================");
        System.out.println("            Struk Pembelian Buku              ");
        System.out.println("=====================================");
        System.out.println("Judul: " + super.getJudul());
        System.out.println("Penulis: " + super.getPenulis());
        System.out.println("Penerbit: " + super.getPenerbit());
        System.out.println("Tahun terbit: " + super.getTahunTerbit());
        System.out.println("Harga: " + super.getHarga());
        System.out.println("Jumlah beli: " + jumlahBeli);
        System.out.println("Total harga: " + totalHarga);
        System.out.println("=====================================");
    }
    
    @Override
    public double hitPajak(){
        return pajak * 0.1 + 1500;
    }
}
