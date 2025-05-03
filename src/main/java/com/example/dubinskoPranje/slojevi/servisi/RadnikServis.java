package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.DTO.GetRadnik;
import com.example.dubinskoPranje.DTO.SetRadnik;
import com.example.dubinskoPranje.entiteti.Radnik;
import com.example.dubinskoPranje.slojevi.repoi.RadnikRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadnikServis {

    private final RadnikRepo radnikRepo;

    @Autowired
    public RadnikServis(RadnikRepo radnikRepo) {
        this.radnikRepo = radnikRepo;
    }

    public List<GetRadnik> getAllRadnici() {
        return radnikRepo.findAll().stream()
                .map(radnik -> new GetRadnik(
                        radnik.getId(),
                        radnik.getIme(),
                        radnik.getPrezime(),
                        radnik.getBrTelefon(),
                        radnik.getMejl()
                )).toList();
    }

    public GetRadnik createRadnik(SetRadnik setRadnik) {
        Radnik radnik = Radnik.builder()
                .ime(setRadnik.getIme())
                .prezime(setRadnik.getPrezime())
                .brTelefon(setRadnik.getBrTelefon())
                .mejl(setRadnik.getMejl())
                .sifra(setRadnik.getSifra())
                .build();

        Radnik saved = radnikRepo.save(radnik);
        return new GetRadnik(
                saved.getId(),
                saved.getIme(),
                saved.getPrezime(),
                saved.getBrTelefon(),
                saved.getMejl()
        );
    }

    public void deleteRadnik(Long id) {
        radnikRepo.deleteById(id);
    }
}

