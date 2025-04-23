package com.example.dubinskoPranje.slojevi.servisi;

import com.example.dubinskoPranje.DTO.CreateRezervacija;
import com.example.dubinskoPranje.DTO.GetRezervacija;
import com.example.dubinskoPranje.DTO.UslugaSaDetaljima;
import com.example.dubinskoPranje.entiteti.Klijent;
import com.example.dubinskoPranje.entiteti.Rezervacija;
import com.example.dubinskoPranje.entiteti.VrsteUsluga;
import com.example.dubinskoPranje.slojevi.repoi.RezervacijaRepo;
import com.example.dubinskoPranje.slojevi.servisi.KlijentServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RezervacijaServis {

    private final RezervacijaRepo rezervacijaRepo;
    private final VrsteUslugaServis vrsteUslugaServis;
    private final KlijentServis klijentServis;

    @Autowired
    public RezervacijaServis(RezervacijaRepo rezervacijaRepo, VrsteUslugaServis vrsteUslugaServis, KlijentServis klijentServis) {
        this.rezervacijaRepo = rezervacijaRepo;
        this.vrsteUslugaServis = vrsteUslugaServis;
        this.klijentServis = klijentServis;
    }

    // Create reservation method (now handles the DTO conversion)
    public GetRezervacija createRezervacija(CreateRezervacija rezervacijaDTO) {
        // Find the client by email from the DTO
        Klijent klijent = klijentServis.findKlijentByMejl(rezervacijaDTO.getMejl());
        if (klijent == null) {
            throw new RuntimeException("Klijent not found with email: " + rezervacijaDTO.getMejl());
        }

        // Validate service details
        if (rezervacijaDTO.getUslugeIds() == null || rezervacijaDTO.getUslugeIds().isEmpty()) {
            throw new IllegalArgumentException("At least one service must be selected.");
        }
        if (rezervacijaDTO.getDetaljiUsluga() == null || rezervacijaDTO.getDetaljiUsluga().size() != rezervacijaDTO.getUslugeIds().size()) {
            throw new IllegalArgumentException("Each service must have corresponding details.");
        }

        // Create the Rezervacija entity
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setKlijent(klijent);
        rezervacija.setAdresa(rezervacijaDTO.getAdresa());
        rezervacija.setDatumVreme(rezervacijaDTO.getDatumVreme());
        rezervacija.setNapomena(rezervacijaDTO.getNapomena());
        rezervacija.setDetaljiUsluga(rezervacijaDTO.getDetaljiUsluga());
        rezervacija.setUslugeIds(rezervacijaDTO.getUslugeIds());

        // Save the reservation
        Rezervacija savedRezervacija = rezervacijaRepo.save(rezervacija);

        // Return the reservation with populated service names
        return populateUslugeNazivi(List.of(savedRezervacija)).get(0);
    }

    public List<GetRezervacija> getAllRezervacije() {
        List<Rezervacija> rezervacije = rezervacijaRepo.findAll();
        return populateUslugeNazivi(rezervacije);
    }

    public void deleteRezervacija(Long id) {
        if (!rezervacijaRepo.existsById(id)) {
            throw new RuntimeException("Rezervacija with id " + id + " does not exist");
        }
        rezervacijaRepo.deleteById(id);
    }

    public List<GetRezervacija> getRezervacijeByKlijentId(Long klijentId) {
        List<Rezervacija> rezervacije = rezervacijaRepo.findByKlijentId(klijentId);
        return populateUslugeNazivi(rezervacije);
    }

    private List<GetRezervacija> populateUslugeNazivi(List<Rezervacija> rezervacije) {
        return rezervacije.stream().map(rezervacija -> {
            List<UslugaSaDetaljima> usluge = new ArrayList<>();
            List<Long> uslugeIds = rezervacija.getUslugeIds();
            List<String> detalji = rezervacija.getDetaljiUsluga();

            for (int i = 0; i < uslugeIds.size(); i++) {
                Long uslugaId = uslugeIds.get(i);
                String detalj = (detalji != null && detalji.size() > i) ? detalji.get(i) : "";
                String ime = vrsteUslugaServis.findVrsteUslugaById(uslugaId)
                        .map(VrsteUsluga::getIme)
                        .orElse("Nepoznata usluga");
                usluge.add(new UslugaSaDetaljima(ime, detalj));
            }

            return GetRezervacija.builder()
                    .id(rezervacija.getId())
                    .datumVreme(rezervacija.getDatumVreme())
                    .adresa(rezervacija.getAdresa())
                    .usluge(usluge)
                    .build();
        }).collect(Collectors.toList());
    }
}


