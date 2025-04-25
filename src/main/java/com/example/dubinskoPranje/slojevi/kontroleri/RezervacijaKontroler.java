package com.example.dubinskoPranje.slojevi.kontroleri;

import com.example.dubinskoPranje.DTO.CreateRezervacija;
import com.example.dubinskoPranje.DTO.GetRezervacija;
import com.example.dubinskoPranje.slojevi.servisi.RezervacijaServis;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rezervacije")
public class RezervacijaKontroler {

    private final RezervacijaServis rezervacijaServis;

    public RezervacijaKontroler(RezervacijaServis rezervacijaServis) {
        this.rezervacijaServis = rezervacijaServis;
    }

    // ---------- Rezervacija Endpoints ----------
    @GetMapping("/")
    public List<GetRezervacija> getAllRezervacije() {
        return rezervacijaServis.getAllRezervacije();
    }

    @PostMapping("/")
    public GetRezervacija createRezervacija(@RequestBody CreateRezervacija rezervacijaDTO) {
        return rezervacijaServis.createRezervacija(rezervacijaDTO);
    }
/*
    @PutMapping("/{id}")
    public Rezervacija updateRezervacija(@PathVariable Long id, @RequestBody Rezervacija rezervacija) {
        return rezervacijaServis.updateRezervacija(id, rezervacija);
    }*/

    @DeleteMapping("/{id}")
    public void deleteRezervacija(@PathVariable Long id) {
        rezervacijaServis.deleteRezervacija(id);
    }

    @GetMapping("/klijent/{klijentId}")
    public List<GetRezervacija> getRezervacijeByKlijentId(@PathVariable Long klijentId) {
        return rezervacijaServis.getRezervacijeByKlijentId(klijentId);
    }
}
