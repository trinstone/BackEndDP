package com.example.dubinskoPranje.slojevi;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.VrsteUslugaRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Konfig {

    @Bean
    CommandLineRunner commandLineRunner(VrsteUslugaRepo vrsteUslugaRepo) {
        return args -> {
            VrsteUsluga stolica = VrsteUsluga.builder()
                    .ime("Stolica")
                    .cena(150)
                    .opis("pranje stolica")
                    .build();
            VrsteUsluga trosed = VrsteUsluga.builder()
                    .ime("Trosed")
                    .cena(600)
                    .opis("pranje trosed")
                    .build();
            VrsteUsluga tepih = VrsteUsluga.builder()
                    .ime("Tepih")
                    .cena(200)
                    .opis("pranje tepih")
                    .build();
            VrsteUsluga auto = VrsteUsluga.builder()
                    .ime("Auto")
                    .cena(150)
                    .opis("pranje auto")
                    .build();
            VrsteUsluga tvrdeP = VrsteUsluga.builder()
                    .ime("Tvrde površine")
                    .cena(200)
                    .opis("pranje tvrd")
                    .build();
            VrsteUsluga stakleneP = VrsteUsluga.builder()
                    .ime("Staklene površine")
                    .cena(300)
                    .opis("pranje stakla")
                    .build();
            List<VrsteUsluga> vrsteUslugaList = Arrays.asList(stolica, trosed, tepih, auto, tvrdeP, stakleneP);
            vrsteUslugaRepo.saveAll(vrsteUslugaList);
        };
    }
}
