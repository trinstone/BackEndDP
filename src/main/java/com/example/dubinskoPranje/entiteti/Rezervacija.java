package com.example.dubinskoPranje.entiteti;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;

public class Rezervacija {
    @Id
    private Long id;
    private LocalDateTime datumVreme;
    private String adresa;
    @ManyToOne
    @JoinColumn(name = "klijent_id")  // Name of the foreign key column
    private Klijent klijent;
    @ElementCollection
    @CollectionTable(name = "rezervacija_usluga", joinColumns = @JoinColumn(name = "rezervacija_id"))
    @Column(name = "usluga_id")
    private List<Long> usluge;  // List of ids of VrsteUsluga
    private String napomena;

    public Rezervacija(Long id, LocalDateTime datumVreme,
                       String adresa, Klijent klijent, List<Long> usluge, String napomena) {
        this.id = id;
        this.datumVreme = datumVreme;
        this.adresa = adresa;
        this.klijent = klijent;
        this.usluge = usluge;
        this.napomena = napomena;
    }

    public Rezervacija(LocalDateTime datumVreme,
                       String napomena, String adresa, Klijent klijent, List<Long> usluge) {
        this.datumVreme = datumVreme;
        this.napomena = napomena;
        this.adresa = adresa;
        this.klijent = klijent;
        this.usluge = usluge;
    }

    public Rezervacija() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public List<Long> getUsluge() {
        return usluge;
    }

    public void setUsluge(List<Long> usluge) {
        this.usluge = usluge;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "Rezervacija{" +
                "id=" + id +
                ", datumVreme=" + datumVreme +
                ", adresa='" + adresa + '\'' +
                ", klijent=" + klijent +
                ", usluge=" + usluge +
                ", napomena='" + napomena + '\'' +
                '}';
    }
}
