package com.example.car_shering;

public class Car {

    private int id_pojazdu;
    private String typ_nadwozia;
    private String marka_pojazdu;
    private int cana;
    private float pojemnosc;
    private String rodzaj_paliwa;
    private int przebieg;

    public Car(int id_pojazdu, String typ_nadwozia, String marka_pojazdu, int cana, float pojemnosc, String rodzaj_paliwa, int przebieg) {
        this.id_pojazdu = id_pojazdu;
        this.typ_nadwozia = typ_nadwozia;
        this.marka_pojazdu = marka_pojazdu;
        this.cana = cana;
        this.pojemnosc = pojemnosc;
        this.rodzaj_paliwa = rodzaj_paliwa;
        this.przebieg = przebieg;
    }

    public int getId_pojazdu() {
        return id_pojazdu;
    }

    public String getTyp_nadwozia() {
        return typ_nadwozia;
    }

    public String getMarka_pojazdu() {
        return marka_pojazdu;
    }

    public int getCana() {
        return cana;
    }

    public float getPojemnosc() {
        return pojemnosc;
    }

    public String getRodzaj_paliwa() {
        return rodzaj_paliwa;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id_pojazdu='" + id_pojazdu + '\'' +
                ", typ_nadwozia='" + typ_nadwozia + '\'' +
                ", marka_pojazdu='" + marka_pojazdu + '\'' +
                ", cana=" + cana +
                ", pojemnosc=" + pojemnosc +
                ", rodzaj_paliwa='" + rodzaj_paliwa + '\'' +
                ", przebieg=" + przebieg +
                '}';
    }
}
