/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BAB6Abstract;

/**
 *
 * @author Wisan
 */
public class Mobil extends Kendaraan{
    int kecepatan;
    int jmlkursi;
    String kelas(){
        return "Mobil";
    }
    @Override
    String bergerak()
    {
        return " Bergerak maju dan mundur";
    }
}
