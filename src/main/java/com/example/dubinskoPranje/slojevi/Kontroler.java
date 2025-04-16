package com.example.dubinskoPranje.slojevi;

import com.example.dubinskoPranje.DTO.CreateRezervacija;
import com.example.dubinskoPranje.DTO.GetRezervacija;
import com.example.dubinskoPranje.DTO.UslugaSaDetaljima;
import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.entiteti.Rezervacija;
import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.servisi.KlijentServis;
import com.example.dubinskoPranje.slojevi.servisi.RezervacijaServis;
import com.example.dubinskoPranje.slojevi.servisi.VrsteUslugaServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public Rezervacija createRezervacija(@RequestBody CreateRezervacija rezervacijaDTO) {
        if (rezervacijaDTO.getMejl() == null) {
            throw new RuntimeException("Klijent ID is missing");
        }

        Klijent klijent = klijentServis.findKlijentByMejl(rezervacijaDTO.getMejl());
        if (klijent == null) {
            throw new RuntimeException("Klijent not found with ID: " + rezervacijaDTO.getMejl());
        }

        Rezervacija rezervacija = Rezervacija.builder()
                .adresa(rezervacijaDTO.getAdresa())
                .datumVreme(rezervacijaDTO.getDatumVreme())
                .napomena(rezervacijaDTO.getNapomena())
                .detaljiUsluga(rezervacijaDTO.getDetaljiUsluga())
                .uslugeIds(rezervacijaDTO.getUslugeIds())
                .klijent(klijent)
                .version(0).build();

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

    @GetMapping("/rezervacije/klijent/{klijentId}")
    public List<GetRezervacija> getRezervacijeByKlijentId(@PathVariable Long klijentId) {
        // Fetch the reservations for the given client ID
        List<Rezervacija> rezervacije = rezervacijaServis.getRezervacijeByKlijentId(klijentId);

        // For each reservation, populate the service names (uslugeNazivi)
        for (Rezervacija rezervacija : rezervacije) {
            // Retrieve and map service names from uslugeIds
            List<String> uslugeNazivi = rezervacija.getUslugeIds().stream()
                    .map(uslugaId -> vrsteUslugaServis.findVrsteUslugaById(uslugaId)
                            .map(VrsteUsluga::getIme) // Extract service name if present
                            .orElse(null)) // Return null if service is not found
                    .filter(Objects::nonNull) // Remove null values from the list
                    .collect(Collectors.toList());

            // Set the service names to the reservation object
            rezervacija.setUslugeNazivi(uslugeNazivi);
        }
        List<GetRezervacija> gr = rezervacije.stream().map((rezervacija ->{
            List<UslugaSaDetaljima> l = new ArrayList<>(rezervacija.getUslugeIds().size());
            for (int i = 0; i < rezervacija.getUslugeIds().size(); i++) {
                l.add(new UslugaSaDetaljima(rezervacija.getUslugeNazivi().get(i),rezervacija.getDetaljiUsluga().get(i)));
            }
            return GetRezervacija.builder()
                    .id(rezervacija.getId())
                    .datumVreme(rezervacija.getDatumVreme())
                    .adresa(rezervacija.getAdresa())
                    .usluge(l)
                    .build();

        })).toList();

        return gr;
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

    // ---------- New Endpoint for VrsteUsluga by IDs ----------
    @GetMapping("/usluge/by-ids")
    public List<VrsteUsluga> getVrsteUslugaByIds(@RequestParam List<Long> ids) {
        return vrsteUslugaServis.getVrsteUslugaByIds(ids);  // Call the service to get VrsteUsluga by IDs
    }
}

