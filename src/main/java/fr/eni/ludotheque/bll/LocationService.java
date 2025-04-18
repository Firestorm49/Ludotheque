package fr.eni.ludotheque.bll;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.eni.ludotheque.classes.Exemplaire;
import fr.eni.ludotheque.classes.Facture;
import fr.eni.ludotheque.classes.Location;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.FactureRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.eni.ludotheque.classes.Jeu;
import fr.eni.ludotheque.dal.JeuRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private FactureRepository factureRepository;

    /**
     * Crée une location à partir du code-barre d'un exemplaire.
     */
    public Location createLocationFromBarcode(String codeBarre) {
        Exemplaire ex = exemplaireRepository.findByCode_barre(codeBarre)
                .orElseThrow(() -> new RuntimeException("Exemplaire non trouvé"));
        return createLocation(ex.getNo_exemplaire(), new Date());
    }

    /**
     * Crée une location pour un exemplaire donné.
     */
    public Location createLocation(int noExemplaire, Date dateDebut) {
        Exemplaire ex = exemplaireRepository.findById(noExemplaire)
                .orElseThrow(() -> new RuntimeException("Exemplaire non trouvé"));
        if (!ex.getLouable()) {
            throw new RuntimeException("Exemplaire non louable");
        }
        ex.setLouable(false);
        exemplaireRepository.save(ex);

        Location loc = new Location();
        loc.setDate_debut(dateDebut);
        loc.setTarif_jour(ex.getJeu().getTarif_jour());
        loc.setExemplaire(ex);
        return locationRepository.save(loc);
    }

    /**
     * Retourne les locations (retour des jeux) et génère la facture.
     */
    public Facture returnLocations(List<Integer> locationIds) {
        List<Location> locs = locationRepository.findAllById(locationIds);
        for (Location l : locs) {
            l.setDate_fin(new Date());
            Exemplaire ex = l.getExemplaire();
            ex.setLouable(true);
            locationRepository.save(l);
            exemplaireRepository.save(ex);
        }
        return createFactureForLocations(locationIds);
    }

    /**
     * Calcule le total et crée la facture pour une liste de locations.
     */
    public Facture createFactureForLocations(List<Integer> locationIds) {
        List<Location> locs = locationRepository.findAllById(locationIds);
        double total = locs.stream()
                .mapToDouble(l -> {
                    Date end = Optional.ofNullable(l.getDate_fin()).orElse(new Date());
                    long days = ChronoUnit.DAYS.between(
                            l.getDate_debut().toInstant(), end.toInstant());
                    return days * l.getTarif_jour();
                })
                .sum();

        Facture facture = new Facture();
        facture.setDate_paiement(new Date());
        return factureRepository.save(facture);
    }
}