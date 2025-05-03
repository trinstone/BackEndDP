package com.example.dubinskoPranje.slojevi.kontroleri;

import com.example.dubinskoPranje.DTO.GetRadnik;
import com.example.dubinskoPranje.DTO.SetRadnik;
import com.example.dubinskoPranje.slojevi.servisi.RadnikServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/radnici")
public class RadnikKontroler {

    private final RadnikServis radnikServis;

    @Autowired
    public RadnikKontroler(RadnikServis radnikServis) {
        this.radnikServis = radnikServis;
    }

    @GetMapping
    public List<GetRadnik> getAllRadnici() {
        return radnikServis.getAllRadnici();
    }

    @PostMapping
    public GetRadnik createRadnik(@RequestBody SetRadnik setRadnik) {
        return radnikServis.createRadnik(setRadnik);
    }

    @DeleteMapping("/{id}")
    public void deleteRadnik(@PathVariable Long id) {
        radnikServis.deleteRadnik(id);
    }
}

