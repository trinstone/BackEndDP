// Osoba.java
package com.example.dubinskoPranje.entiteti;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Osoba {
    protected String ime;
    protected String prezime;
    protected String brTelefon;

    @Column(unique = true, nullable = false)
    protected String mejl;

    protected String sifra;
}

