package com.example.dubinskoPranje.slojevi.kontroleri;

import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.slojevi.servisi.KlijentServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/klijenti")
public class KlijentKontroler {
    private final KlijentServis klijentServis;

    @Autowired
    public KlijentKontroler(KlijentServis klijentServis) {
        this.klijentServis = klijentServis;
    }

    @GetMapping("/")
    public List<Klijent> getAllKlijenti() {
        return klijentServis.getAllKlijenti();
    }

    @PostMapping("/")
    public Klijent createKlijent(@RequestBody Klijent klijent) {
        return klijentServis.createKlijent(klijent);
    }

    @PutMapping("/{id}")
    public Klijent updateKlijent(@PathVariable Long id, @RequestBody Klijent klijent) {
        return klijentServis.updateKlijent(id, klijent);
    }

    @DeleteMapping("/{id}")
    public void deleteKlijent(@PathVariable Long id) {
        klijentServis.deleteKlijent(id);
    }
}
