/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectDatabase;
/**
 *
 * @author Wisan
 */
public class Pendapatan extends DataPenjualanBuku{
    private int jumlahTerjual;

    // Konstruktor
    public Pendapatan(String judulBuku, String penulis, double harga, String tanggalPenjualan, int jumlahTerjual) {
        super(judulBuku, penulis, harga, tanggalPenjualan);
        this.jumlahTerjual = jumlahTerjual;
    }

    // Implementasi metode abstrak dari kelas induk
    @Override
    public double hitungTotalPendapatan() {
        return getHarga() * jumlahTerjual;
    }

    // Metode Getters dan Setters khusus untuk kelas Pendapatan
    public int getJumlahTerjual() {
        return jumlahTerjual;
    }

    public void setJumlahTerjual(int jumlahTerjual) {
        this.jumlahTerjual = jumlahTerjual;
    }

    // Metode toString untuk representasi String objek Pendapatan
    @Override
    public String toString() {
        return "Pendapatan{" +
                "judulBuku='" + getJudulBuku() + '\'' +
                ", penulis='" + getPenulis() + '\'' +
                ", harga=" + getHarga() +
                ", tanggalPenjualan=" + getTanggalPenjualan() +
                ", jumlahTerjual=" + jumlahTerjual +
                '}';
    }
}
