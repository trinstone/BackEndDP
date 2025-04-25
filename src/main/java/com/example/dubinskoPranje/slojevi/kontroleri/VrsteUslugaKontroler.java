package com.example.dubinskoPranje.slojevi.kontroleri;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.servisi.VrsteUslugaServis;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VrsteUslugaKontroler {

    private final VrsteUslugaServis vrsteUslugaServis;

    public VrsteUslugaKontroler(VrsteUslugaServis vrsteUslugaServis) {
        this.vrsteUslugaServis = vrsteUslugaServis;
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
