package com.example.dubinskoPranje.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class GetRezervacija {
    Long id;
    LocalDateTime datumVreme;
    String adresa;
    List<UslugaSaDetaljima> usluge;
}
