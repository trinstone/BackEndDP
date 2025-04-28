package com.example.dubinskoPranje.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class VrsteUsluga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String opis;
    private Integer cena;

    @Version
    private int version;

    public VrsteUsluga(String ime, String opis, Integer cena) {
        this.ime = ime;
        this.opis = opis;
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


