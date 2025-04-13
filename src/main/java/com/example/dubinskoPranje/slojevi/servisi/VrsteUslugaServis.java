package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.VrsteUslugaRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VrsteUslugaServis {

    private final VrsteUslugaRepo vrsteUslugaRepo;

    @Autowired
    public VrsteUslugaServis(VrsteUslugaRepo vrsteUslugaRepo) {
        this.vrsteUslugaRepo = vrsteUslugaRepo;
    }

    // Get all available services
    public List<VrsteUsluga> getAllVrsteUsluga() {
        return vrsteUslugaRepo.findAll();
    }

    // Create a new service
    public VrsteUsluga createVrsteUsluga(VrsteUsluga usluga) {
        return vrsteUslugaRepo.save(usluga);
    }

    // Update an existing service
    @Transactional
    public VrsteUsluga updateVrsteUsluga(Long id, VrsteUsluga updatedUsluga) {
        VrsteUsluga existing = vrsteUslugaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VrsteUsluga not found with id " + id));

        existing.setIme(updatedUsluga.getIme());
        // You can add more fields to update here if necessary.
        return vrsteUslugaRepo.save(existing);
    }

    // Delete a service by ID
    public void deleteVrsteUsluga(Long id) {
        if (!vrsteUslugaRepo.existsById(id)) {
            throw new EntityNotFoundException("VrsteUsluga not found with id " + id);
        }
        vrsteUslugaRepo.deleteById(id);
    }

    // Fetch a service by ID and return an Optional
    public Optional<VrsteUsluga> findVrsteUslugaById(Long id) {
        return vrsteUslugaRepo.findById(id);
    }

    // Fetch multiple services by IDs
    public List<VrsteUsluga> getVrsteUslugaByIds(List<Long> ids) {
        return vrsteUslugaRepo.findAllById(ids);
    }
}
