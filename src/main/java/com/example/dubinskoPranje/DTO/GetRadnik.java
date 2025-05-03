package com.example.dubinskoPranje.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRadnik {
    private Long id;
    private String ime;
    private String prezime;
    private String brTelefon;
    private String mejl;
}

