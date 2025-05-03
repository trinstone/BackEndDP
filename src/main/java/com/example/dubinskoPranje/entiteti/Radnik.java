package com.example.dubinskoPranje.entiteti;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Radnik extends Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    @Override
    public String toString() {
        return "Radnik{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", brTelefon='" + brTelefon + '\'' +
                ", mejl='" + mejl + '\'' +
                ", sifra='" + sifra + '\'' +
                '}';
    }
}
