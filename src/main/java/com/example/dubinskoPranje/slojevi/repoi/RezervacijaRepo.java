package com.example.dubinskoPranje.slojevi.repoi;

import com.example.dubinskoPranje.entiteti.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezervacijaRepo extends JpaRepository<Rezervacija, Long> {
    List<Rezervacija> findByKlijentId(Long klijentId);
}

