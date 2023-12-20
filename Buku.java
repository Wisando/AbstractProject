/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectDatabase;

/**
 *
 * @author Wisan
 */
public class Buku {
    // atribut
    String judul, penulis, penerbit;
    int tahunTerbit;
    double harga;
    double pajak;
    
    // constructor
   public Buku() {
       super();
        this.judul = "Harry Potter and the Philosopher's Stone";
        this.penulis = "J.K. Rowling";
        this.penerbit = "Bloomsbury";
        this.tahunTerbit = 1997;
        this.harga = 75000;
    }
   public Buku (double pajak){
       this.pajak = pajak;
   }
   public double hitPajak(){
       return pajak * 0.1;
   }
    // getter dan setter
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
    // metode polimorfisme
    public void displayInfo() {
        System.out.println("Judul: " + judul);
        System.out.println("Penulis: " + penulis);
        System.out.println("Penerbit: " + penerbit);
        System.out.println("Tahun Terbit: " + tahunTerbit);
        System.out.println("Harga: " + harga);
    }
}

