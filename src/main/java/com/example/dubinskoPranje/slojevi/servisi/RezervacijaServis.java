package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.entiteti.Rezervacija;
import com.example.dubinskoPranje.slojevi.repoi.RezervacijaRepo;  // Fixed import to use RezervacijaRepo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaServis {

    private final RezervacijaRepo rezervacijaRepo;  // Use RezervacijaRepo instead of RezervacijaRepository

    @Autowired
    public RezervacijaServis(RezervacijaRepo rezervacijaRepo) {  // Constructor injection
        this.rezervacijaRepo = rezervacijaRepo;
    }

    // Get all Rezervacije
    public List<Rezervacija> getAllRezervacije() {
        return rezervacijaRepo.findAll();  // Using rezervacijaRepo instead of rezervacijaRepository
    }

    // Create a new Rezervacija
    public Rezervacija createRezervacija(Rezervacija rezervacija) {
        return rezervacijaRepo.save(rezervacija);  // Using rezervacijaRepo
    }

    // Update an existing Rezervacija
    public Rezervacija updateRezervacija(Long id, Rezervacija rezervacija) {
        Optional<Rezervacija> existingRezervacija = rezervacijaRepo.findById(id);  // Using rezervacijaRepo
        if (existingRezervacija.isPresent()) {
            Rezervacija existing = existingRezervacija.get();
            existing.setKlijent(rezervacija.getKlijent());  // Assuming it's a Many-to-One relation
            existing.setUsluge(rezervacija.getUsluge());  // Update usluga if applicable
            // Update other fields if needed
            return rezervacijaRepo.save(existing);  // Using rezervacijaRepo
        }
        throw new RuntimeException("Rezervacija not found with id " + id);
    }

    // Delete a Rezervacija
    public void deleteRezervacija(Long id) {
        rezervacijaRepo.deleteById(id);  // Using rezervacijaRepo
    }
}


