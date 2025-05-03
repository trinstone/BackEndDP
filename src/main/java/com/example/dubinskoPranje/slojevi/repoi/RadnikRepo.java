package com.example.dubinskoPranje.slojevi.repoi;

import com.example.dubinskoPranje.entiteti.Radnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadnikRepo extends JpaRepository<Radnik, Long> {
    Radnik findByMejl(String mejl);
}

