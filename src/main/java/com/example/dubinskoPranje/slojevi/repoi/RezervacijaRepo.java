package com.example.dubinskoPranje.slojevi.repoi;

import com.example.dubinskoPranje.entiteti.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezervacijaRepo extends JpaRepository<Rezervacija, Long> {
    // Custom query methods (if needed) can be added here
}

