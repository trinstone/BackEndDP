package com.example.dubinskoPranje.entiteti;

import jakarta.persistence.Id;

public class VrsteUsluga {
    @Id
    private Long id;
    private String ime;
    private String opis;
    private Integer cena;

    public VrsteUsluga(Long id, String ime, String opis, Integer cena) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
        this.cena = cena;
    }

    public VrsteUsluga(String ime, Integer cena, String opis) {
        this.ime = ime;
        this.cena = cena;
        this.opis = opis;
    }

    public VrsteUsluga() {
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "VrsteUsluga{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                '}';
    }
}
