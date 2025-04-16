package com.example.dubinskoPranje.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class CreateRezervacija {
        LocalDateTime datumVreme;
        String adresa ;
        String mejl;
        List<Long> uslugeIds;
        List<String> detaljiUsluga;
        String napomena;
}
