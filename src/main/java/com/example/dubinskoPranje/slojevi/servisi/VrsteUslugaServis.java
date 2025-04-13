package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.VrsteUslugaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VrsteUslugaServis {

    private final VrsteUslugaRepo vrsteUslugaRepo;

    @Autowired
    public VrsteUslugaServis(VrsteUslugaRepo vrsteUslugaRepo) {
        this.vrsteUslugaRepo = vrsteUslugaRepo;
    }

    public List<VrsteUsluga> getAllVrsteUsluga() {
        return vrsteUslugaRepo.findAll();
    }

    public VrsteUsluga createVrsteUsluga(VrsteUsluga usluga) {
        return vrsteUslugaRepo.save(usluga);
    }

    public VrsteUsluga updateVrsteUsluga(Long id, VrsteUsluga updatedUsluga) {
        VrsteUsluga existing = vrsteUslugaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("VrsteUsluga not found with id " + id));

        existing.setIme(updatedUsluga.getIme());
        return vrsteUslugaRepo.save(existing);
    }

    public void deleteVrsteUsluga(Long id) {
        vrsteUslugaRepo.deleteById(id);
    }

    public VrsteUsluga findVrsteUslugaById(Long id) {
        return vrsteUslugaRepo.findById(id).orElse(null);
    }

    public List<VrsteUsluga> getVrsteUslugaByIds(List<Long> ids) {
        return vrsteUslugaRepo.findAllById(ids);
    }
}
