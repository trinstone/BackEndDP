package com.example.dubinskoPranje.slojevi;

import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.slojevi.repoi.KlijentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class Konfig {

    @Bean
    CommandLineRunner commandLineRunner(KlijentRepo klijentRepo) {
        return args -> {

        };
    }
}

