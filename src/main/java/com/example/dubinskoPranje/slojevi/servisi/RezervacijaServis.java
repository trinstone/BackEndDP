package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.entiteti.Rezervacija;
import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.RezervacijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaServis {

    private final RezervacijaRepo rezervacijaRepo;
    private final VrsteUslugaServis vrsteUslugaServis;

    @Autowired
    public RezervacijaServis(RezervacijaRepo rezervacijaRepo, VrsteUslugaServis vrsteUslugaServis) {
        this.rezervacijaRepo = rezervacijaRepo;
        this.vrsteUslugaServis = vrsteUslugaServis;
    }

    public List<Rezervacija> getAllRezervacije() {
        List<Rezervacija> rezervacije = rezervacijaRepo.findAll();
        rezervacije.forEach(this::populateUslugeNazivi);
        return rezervacije;
    }

    public Rezervacija createRezervacija(Rezervacija rezervacija) {
        Rezervacija saved = rezervacijaRepo.save(rezervacija);
        populateUslugeNazivi(saved);
        return saved;
    }

    public Rezervacija updateRezervacija(Long id, Rezervacija rezervacija) {
        Rezervacija existing = rezervacijaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezervacija not found with id " + id));

        existing.setKlijent(rezervacija.getKlijent());
        existing.setUsluge(rezervacija.getUsluge());

        Rezervacija updated = rezervacijaRepo.save(existing);
        populateUslugeNazivi(updated);
        return updated;
    }

    public void deleteRezervacija(Long id) {
        if (!rezervacijaRepo.existsById(id)) {
            throw new RuntimeException("Rezervacija with id " + id + " does not exist");
        }
        rezervacijaRepo.deleteById(id);
    }

    public List<Rezervacija> getRezervacijeByKlijentId(Long klijentId) {
        List<Rezervacija> rezervacije = rezervacijaRepo.findByKlijentId(klijentId);
        rezervacije.forEach(this::populateUslugeNazivi);
        return rezervacije;
    }

    private void populateUslugeNazivi(Rezervacija rezervacija) {
        List<Long> uslugeIds = rezervacija.getUsluge();
        List<String> nazivList = new ArrayList<>();
        if (uslugeIds != null) {
            for (Long id : uslugeIds) {
                VrsteUsluga usluga = vrsteUslugaServis.findVrsteUslugaById(id);
                if (usluga != null) {
                    nazivList.add(usluga.getIme());
                }
            }
        }
        rezervacija.setUslugeNazivi(nazivList); // Set the service names in the reservation
    }
}
