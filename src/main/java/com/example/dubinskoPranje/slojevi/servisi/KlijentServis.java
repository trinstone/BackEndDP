package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.DTO.GetKlijent;
import com.example.dubinskoPranje.DTO.SetKlijent;
import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.slojevi.repoi.KlijentRepo; // Updated import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<GetKlijent> getAllKlijenti() {
        List<Klijent> k = klijentRepo.findAll();  // Using klijentRepo instead of klijentRepository
        List<GetKlijent> kl = new ArrayList<>();
        for (int i = 0; i < k.size(); i++) {
            kl.add(GetKlijent.builder()
                    .ime(k.get(i).getIme())
                    .prezime(k.get(i).getPrezime())
                    .brTelefon(k.get(i).getBrTelefon())
                    .id(k.get(i).getId())
                    .mejl(k.get(i).getMejl()).build());
        }
        return kl;
    }

    // Get Klijent by ID (new method to fetch by ID)
    public GetKlijent findKlijentById(Long id) {
        Optional<Klijent> klijentOptional = klijentRepo.findById(id);
        if (klijentOptional.isPresent()) {
            Klijent k = klijentOptional.get();
            return GetKlijent.builder()
                    .ime(k.getIme())
                    .prezime(k.getPrezime())
                    .brTelefon(k.getBrTelefon())
                    .id(k.getId())
                    .mejl(k.getMejl()).build();
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
    public GetKlijent createKlijent(SetKlijent klijent) {
        Klijent k1 = Klijent.builder()
                .ime(klijent.getIme())
                .prezime(klijent.getPrezime())
                .brTelefon(klijent.getBrTelefon())
                .sifra(klijent.getSifra())
                .mejl(klijent.getMejl()).build();
        Klijent t =  klijentRepo.save(k1);  // Using klijentRepo
        return GetKlijent.builder()
                .ime(k1.getIme())
                .prezime(k1.getPrezime())
                .brTelefon(k1.getBrTelefon())
                .id(k1.getId())
                .mejl(k1.getMejl()).build();
    }

    // Update an existing Klijent
    public GetKlijent updateKlijent(Long id, SetKlijent klijent) {
        Optional<Klijent> existingKlijent = klijentRepo.findById(id);  // Using klijentRepo
        if (existingKlijent.isPresent()) {
            Klijent existing = existingKlijent.get();
            existing.setIme(klijent.getIme()); // Update first name
            existing.setPrezime(klijent.getPrezime()); // Update last name
            existing.setMejl(klijent.getMejl()); // Update email
            // Update other fields if needed
            Klijent k1 = klijentRepo.save(existing);
            return GetKlijent.builder()
                    .ime(k1.getIme())
                    .prezime(k1.getPrezime())
                    .brTelefon(k1.getBrTelefon())
                    .id(k1.getId())
                    .mejl(k1.getMejl()).build();
        }
        throw new RuntimeException("Klijent not found with id " + id);
    }

    // Delete a Klijent
    public void deleteKlijent(Long id) {
        klijentRepo.deleteById(id);  // Using klijentRepo
    }
}
