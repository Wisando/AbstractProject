/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectDatabase;
/**
 *
 * @author Wisan
 */
 public abstract class DataPenjualanBuku implements PenjualanBukuInterface {
    private String judulBuku;
    private String penulis;
    private double harga;
    private String tanggalPenjualan;

    // Konstruktor
    public DataPenjualanBuku(String judulBuku, String penulis, double harga, String tanggalPenjualan) {
        this.judulBuku = judulBuku;
        this.penulis = penulis;
        this.harga = harga;
        this.tanggalPenjualan = tanggalPenjualan;
    }

    

    // Metode Getters dan Setters
    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getTanggalPenjualan() {
        return tanggalPenjualan;
    }

    public void setTanggalPenjualan(String tanggalPenjualan) {
        this.tanggalPenjualan = tanggalPenjualan;
    }
    // Metode implementasi dari interface
    @Override
    public double hitungTotalPendapatan() {
        // Implementasi metode hitungTotalPendapatan di sini
        return 0;
    }
}
