package com.example.dubinskoPranje.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GetKlijent {
    private Long id;
    private String ime;
    private String prezime;
    private String brTelefon;
    private String mejl;
}
