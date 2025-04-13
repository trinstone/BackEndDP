package com.example.dubinskoPranje.slojevi.repoi;

import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VrsteUslugaRepo extends JpaRepository<VrsteUsluga, Long> {
    List<VrsteUsluga> findAllByIdIn(List<Long> ids);  // Correct method signature for fetching multiple records by IDs
}

