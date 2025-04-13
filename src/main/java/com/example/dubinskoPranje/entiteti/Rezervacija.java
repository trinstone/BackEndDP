package com.example.dubinskoPranje.entiteti;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table
public class Rezervacija {

    @Id
    @SequenceGenerator(
            name = "rezervacija_sequence",
            sequenceName = "rezervacija_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rezervacija_sequence"
    )
    private Long id;

    private LocalDateTime datumVreme;
    private String adresa;

    @ManyToOne
    @JoinColumn(name = "klijent_id")
    private Klijent klijent;

    @ElementCollection
    @CollectionTable(name = "rezervacija_usluga", joinColumns = @JoinColumn(name = "rezervacija_id"))
    @Column(name = "usluga_id")
    private List<Long> uslugeIds;  // List of IDs of VrsteUsluga

    private String napomena;

    @Transient
    private List<String> uslugeNazivi; // Transient field for service names

    @Version
    private int version;

    // Default constructor
    public Rezervacija() {
    }

    // Constructor with all fields (including id)
    public Rezervacija(Long id, LocalDateTime datumVreme, String adresa, Klijent klijent, List<Long> uslugeIds, String napomena, int version) {
        this.id = id;
        this.datumVreme = datumVreme;
        this.adresa = adresa;
        this.klijent = klijent;
        this.uslugeIds = uslugeIds;
        this.napomena = napomena;
        this.version = version;
    }

    // Constructor without the 'id' field
    public Rezervacija(LocalDateTime datumVreme, String adresa, Klijent klijent, List<Long> uslugeIds, String napomena) {
        this.datumVreme = datumVreme;
        this.adresa = adresa;
        this.klijent = klijent;
        this.uslugeIds = uslugeIds;
        this.napomena = napomena;
    }

    // Getters and setters
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

    public List<Long> getUslugeIds() {
        return uslugeIds;
    }

    public void setUslugeIds(List<Long> uslugeIds) {
        this.uslugeIds = uslugeIds;
    }

    public List<String> getUslugeNazivi() {
        return uslugeNazivi;
    }

    public void setUslugeNazivi(List<String> uslugeNazivi) {
        this.uslugeNazivi = uslugeNazivi;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

}



