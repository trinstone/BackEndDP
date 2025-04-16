package com.example.dubinskoPranje.entiteti;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datumVreme;
    private String adresa;

    @ManyToOne
    @JoinColumn(name = "klijent_id")
    private Klijent klijent;

    @CollectionTable(name = "rezervacija_usluga", joinColumns = @JoinColumn(name = "rezervacija_id"))
    @Column(name = "usluga_id")
    private List<Long> uslugeIds;  // List of IDs of VrsteUsluga

    private String napomena;
    private List<String> detaljiUsluga;
    @Transient
    private List<String> uslugeNazivi; // Transient field for service names

    @Version
    private int version;


}



