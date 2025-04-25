package com.example.dubinskoPranje.slojevi.kontroleri;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.servisi.VrsteUslugaServis;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usluge")
public class VrsteUslugaKontroler {

    private final VrsteUslugaServis vrsteUslugaServis;

    public VrsteUslugaKontroler(VrsteUslugaServis vrsteUslugaServis) {
        this.vrsteUslugaServis = vrsteUslugaServis;
    }

    // ---------- VrsteUsluga Endpoints ----------
    @GetMapping("/")
    public List<VrsteUsluga> getAllUsluge() {
        return vrsteUslugaServis.getAllVrsteUsluga();
    }

    @PostMapping("/")
    public VrsteUsluga createUsluga(@RequestBody VrsteUsluga usluga) {
        return vrsteUslugaServis.createVrsteUsluga(usluga);
    }

    @PutMapping("/{id}")
    public VrsteUsluga updateUsluga(@PathVariable Long id, @RequestBody VrsteUsluga usluga) {
        return vrsteUslugaServis.updateVrsteUsluga(id, usluga);
    }

    @DeleteMapping("/{id}")
    public void deleteUsluga(@PathVariable Long id) {
        vrsteUslugaServis.deleteVrsteUsluga(id);
    }

    // ---------- New Endpoint for VrsteUsluga by IDs ----------
    @GetMapping("/by-ids")
    public List<VrsteUsluga> getVrsteUslugaByIds(@RequestParam List<Long> ids) {
        return vrsteUslugaServis.getVrsteUslugaByIds(ids);  // Call the service to get VrsteUsluga by IDs
    }
}
