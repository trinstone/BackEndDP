package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.slojevi.repoi.KlijentRepo; // Updated import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlijentServis {

    private final KlijentRepo klijentRepo;  // Use KlijentRepo instead of KlijentRepository

    @Autowired
    public KlijentServis(KlijentRepo klijentRepo) {  // Constructor injection
        this.klijentRepo = klijentRepo;
    }

    // Get all Klijenti
    public List<Klijent> getAllKlijenti() {
        return klijentRepo.findAll();  // Using klijentRepo instead of klijentRepository
    }

    // Get Klijent by ID (new method to fetch by ID)
    public Klijent findKlijentById(Long id) {
        Optional<Klijent> klijentOptional = klijentRepo.findById(id);
        if (klijentOptional.isPresent()) {
            return klijentOptional.get();
        }
        throw new RuntimeException("Klijent not found with id " + id);
    }
    public Klijent findKlijentByMejl(String mejl) {
        Optional<Klijent> klijent = Optional.ofNullable(klijentRepo.findByMejl(mejl));
        if (klijent.isPresent()) {
            return klijent.get();
        }
        throw new RuntimeException("Klijent not found with mejl " + mejl);
    }

    // Create a new Klijent
    public Klijent createKlijent(Klijent klijent) {
        return klijentRepo.save(klijent);  // Using klijentRepo
    }

    // Update an existing Klijent
    public Klijent updateKlijent(Long id, Klijent klijent) {
        Optional<Klijent> existingKlijent = klijentRepo.findById(id);  // Using klijentRepo
        if (existingKlijent.isPresent()) {
            Klijent existing = existingKlijent.get();
            existing.setIme(klijent.getIme()); // Update first name
            existing.setPrezime(klijent.getPrezime()); // Update last name
            existing.setMejl(klijent.getMejl()); // Update email
            // Update other fields if needed
            return klijentRepo.save(existing);  // Using klijentRepo
        }
        throw new RuntimeException("Klijent not found with id " + id);
    }

    // Delete a Klijent
    public void deleteKlijent(Long id) {
        klijentRepo.deleteById(id);  // Using klijentRepo
    }
}
