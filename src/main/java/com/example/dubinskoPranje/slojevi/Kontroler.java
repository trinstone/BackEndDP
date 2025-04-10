package com.example.dubinskoPranje.slojevi;

import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.entiteti.Rezervacija;
import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.servisi.KlijentServis;
import com.example.dubinskoPranje.slojevi.servisi.RezervacijaServis;
import com.example.dubinskoPranje.slojevi.servisi.VrsteUslugaServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Kontroler {

    private final KlijentServis klijentServis;
    private final RezervacijaServis rezervacijaServis;
    private final VrsteUslugaServis vrsteUslugaServis;

    @Autowired
    public Kontroler(KlijentServis klijentServis, RezervacijaServis rezervacijaServis, VrsteUslugaServis vrsteUslugaServis) {
        this.klijentServis = klijentServis;
        this.rezervacijaServis = rezervacijaServis;
        this.vrsteUslugaServis = vrsteUslugaServis;
    }

    // ---------- Klijent Endpoints ----------
    @GetMapping("/klijenti")
    public List<Klijent> getAllKlijenti() {
        return klijentServis.getAllKlijenti();
    }

    @PostMapping("/klijenti")
    public Klijent createKlijent(@RequestBody Klijent klijent) {
        return klijentServis.createKlijent(klijent);
    }

    @PutMapping("/klijenti/{id}")
    public Klijent updateKlijent(@PathVariable Long id, @RequestBody Klijent klijent) {
        return klijentServis.updateKlijent(id, klijent);
    }

    @DeleteMapping("/klijenti/{id}")
    public void deleteKlijent(@PathVariable Long id) {
        klijentServis.deleteKlijent(id);
    }

    // ---------- Rezervacija Endpoints ----------
    @GetMapping("/rezervacije")
    public List<Rezervacija> getAllRezervacije() {
        return rezervacijaServis.getAllRezervacije();
    }

    @PostMapping("/rezervacije")
    public Rezervacija createRezervacija(@RequestBody Rezervacija rezervacija) {
        // Ensure Klijent is set correctly before saving the reservation
        if (rezervacija.getKlijent() == null || rezervacija.getKlijent().getId() == null) {
            throw new RuntimeException("Klijent ID is missing");
        }

        // Fetch Klijent using the ID provided
        Klijent klijent = klijentServis.findKlijentById(rezervacija.getKlijent().getId());
        if (klijent == null) {
            throw new RuntimeException("Klijent not found with ID: " + rezervacija.getKlijent().getId());
        }
        rezervacija.setKlijent(klijent);  // Set the Klijent in the reservation object

        return rezervacijaServis.createRezervacija(rezervacija);
    }

    @PutMapping("/rezervacije/{id}")
    public Rezervacija updateRezervacija(@PathVariable Long id, @RequestBody Rezervacija rezervacija) {
        return rezervacijaServis.updateRezervacija(id, rezervacija);
    }

    @DeleteMapping("/rezervacije/{id}")
    public void deleteRezervacija(@PathVariable Long id) {
        rezervacijaServis.deleteRezervacija(id);
    }

    // Get reservations by client ID
    @GetMapping("/rezervacije/klijent/{klijentId}")
    public List<Rezervacija> getRezervacijeByKlijentId(@PathVariable Long klijentId) {
        return rezervacijaServis.getRezervacijeByKlijentId(klijentId);
    }

    // ---------- VrsteUsluga Endpoints ----------
    @GetMapping("/usluge")
    public List<VrsteUsluga> getAllUsluge() {
        return vrsteUslugaServis.getAllVrsteUsluga();
    }

    @PostMapping("/usluge")
    public VrsteUsluga createUsluga(@RequestBody VrsteUsluga usluga) {
        return vrsteUslugaServis.createVrsteUsluga(usluga);
    }

    @PutMapping("/usluge/{id}")
    public VrsteUsluga updateUsluga(@PathVariable Long id, @RequestBody VrsteUsluga usluga) {
        return vrsteUslugaServis.updateVrsteUsluga(id, usluga);
    }

    @DeleteMapping("/usluge/{id}")
    public void deleteUsluga(@PathVariable Long id) {
        vrsteUslugaServis.deleteVrsteUsluga(id);
    }
}

