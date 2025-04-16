package com.example.dubinskoPranje.slojevi;

import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.KlijentRepo;
import com.example.dubinskoPranje.slojevi.repoi.VrsteUslugaRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class Konfig {

    @Bean
    CommandLineRunner commandLineRunner(VrsteUslugaRepo vrsteUslugaRepo) {
        return args -> {
            VrsteUsluga stolica = new VrsteUsluga("Stolica", 150, "pranje stolica");
            VrsteUsluga trosed = new VrsteUsluga("Trosed", 600, "pranje trosed");
            VrsteUsluga tepih = new VrsteUsluga("Tepih", 200, "pranje tepih");
            VrsteUsluga auto = new VrsteUsluga("Auto", 150, "pranje auto");
            VrsteUsluga tvrdeP = new VrsteUsluga("Tvrde površine", 200, "pranje tvrd");
            VrsteUsluga stakleneP = new VrsteUsluga("Staklene površine", 300, "pranje stakla");
            vrsteUslugaRepo.saveAll(List.of(stolica, trosed, tepih, auto, tvrdeP, stakleneP));

        };
    }
}

