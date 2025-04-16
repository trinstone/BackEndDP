package com.example.dubinskoPranje.entiteti;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Klijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String prezime;
    private String brTelefon;
    // @UniqueConstraint()
    @Column(unique = true,nullable = false)
    private String mejl;
    private String sifra;

    @Version
    private int version;

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

