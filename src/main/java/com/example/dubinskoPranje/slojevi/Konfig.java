package com.example.dubinskoPranje.slojevi;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.VrsteUslugaRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Konfig {

    @Bean
    CommandLineRunner commandLineRunner(VrsteUslugaRepo vrsteUslugaRepo) {
        return args -> {
            VrsteUsluga stolica = new VrsteUsluga("Stolica", "pranje stolica", 150);
            VrsteUsluga trosed = new VrsteUsluga("Trosed", "pranje trosed", 600);
            VrsteUsluga tepih = new VrsteUsluga("Tepih", "pranje tepih", 200);
            VrsteUsluga auto = new VrsteUsluga("Auto", "pranje auto", 150);
            VrsteUsluga tvrdeP = new VrsteUsluga("Tvrde površine", "pranje tvrd", 200);
            VrsteUsluga stakleneP = new VrsteUsluga("Staklene površine", "pranje stakla", 300);

            vrsteUslugaRepo.saveAll(List.of(stolica, trosed, tepih, auto, tvrdeP, stakleneP));
        };
    }
}