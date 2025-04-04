package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.VrsteUslugaRepo;  // Fixed import to use VrsteUslugaRepo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VrsteUslugaServis {

    private final VrsteUslugaRepo vrsteUslugaRepo;  // Use VrsteUslugaRepo instead of VrsteUslugaRepository

    @Autowired
    public VrsteUslugaServis(VrsteUslugaRepo vrsteUslugaRepo) {  // Constructor injection
        this.vrsteUslugaRepo = vrsteUslugaRepo;
    }

    // Get all VrsteUsluga
    public List<VrsteUsluga> getAllVrsteUsluga() {
        return vrsteUslugaRepo.findAll();  // Using vrsteUslugaRepo instead of vrsteUslugaRepository
    }

    // Create a new VrsteUsluga
    public VrsteUsluga createVrsteUsluga(VrsteUsluga usluga) {
        return vrsteUslugaRepo.save(usluga);  // Using vrsteUslugaRepo
    }

    // Update an existing VrsteUsluga
    public VrsteUsluga updateVrsteUsluga(Long id, VrsteUsluga usluga) {
        Optional<VrsteUsluga> existingUsluga = vrsteUslugaRepo.findById(id);  // Using vrsteUslugaRepo
        if (existingUsluga.isPresent()) {
            VrsteUsluga existing = existingUsluga.get();
            existing.setIme(usluga.getIme());  // Update other fields if needed
            return vrsteUslugaRepo.save(existing);  // Using vrsteUslugaRepo
        }
        throw new RuntimeException("VrsteUsluga not found with id " + id);
    }

    // Delete a VrsteUsluga
    public void deleteVrsteUsluga(Long id) {
        vrsteUslugaRepo.deleteById(id);  // Using vrsteUslugaRepo
    }
}
