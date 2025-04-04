package com.example.dubinskoPranje.slojevi.repoi;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrsteUslugaRepo extends JpaRepository<VrsteUsluga, Long> {
    // Custom query methods (if needed) can be added here
}

