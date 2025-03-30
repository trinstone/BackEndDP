package com.example.dubinskoPranje.entiteti;

import jakarta.persistence.Id;

public class Klijent {
    @Id
    private Long id;
    private String ime;
    private String prezime;
    private String brTelefon;
    private String mejl;
    private String sifra;

    public Klijent() {
    }

    public Klijent(Long id, String ime, String prezime, String brTelefon,
                   String mejl, String sifra) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.brTelefon = brTelefon;
        this.mejl = mejl;
        this.sifra = sifra;
    }

    public Klijent(String sifra, String mejl, String brTelefon, String prezime, String ime) {
        this.sifra = sifra;
        this.mejl = mejl;
        this.brTelefon = brTelefon;
        this.prezime = prezime;
        this.ime = ime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrTelefon() {
        return brTelefon;
    }

    public void setBrTelefon(String brTelefon) {
        this.brTelefon = brTelefon;
    }

    public String getMejl() {
        return mejl;
    }

    public void setMejl(String mejl) {
        this.mejl = mejl;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return "Klijent{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", brTelefon='" + brTelefon + '\'' +
                ", mejl='" + mejl + '\'' +
                ", sifra='" + sifra + '\'' +
                '}';
    }
}
