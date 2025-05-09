package com.example.dubinskoPranje.slojevi.repoi;

import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.slojevi.servisi.KlijentServis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlijentRepo extends JpaRepository<Klijent, Long> {
    // Custom query methods (if needed) can be added here

    Klijent findByMejl(String mejl);
}


