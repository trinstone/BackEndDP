package com.example.dubinskoPranje.DTO;

import com.example.dubinskoPranje.entiteti.Klijent;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
public class CreateRezervacija {
        Long id;
        LocalDateTime datumVreme;
        String adresa ;
        Klijent klijent;
        List<Long> uslugeIds;
        List<String> detaljiUsluga;
        String napomena;
}
