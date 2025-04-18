package fr.eni.ludotheque.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.eni.ludotheque.classes.Exemplaire;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.eni.ludotheque.classes.Jeu;
import fr.eni.ludotheque.dal.JeuRepository;

@Service
public class JeuService {

    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Jeu> findAll() {
        return jeuRepository.findAll();
    }

    public Jeu findById(int noJeu) {
        return jeuRepository.findById(noJeu)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Jeu saveJeu(Jeu jeu) {
        return jeuRepository.save(jeu);
    }

    public Jeu updateJeu(int noJeu, Jeu jeu) {
        Jeu existing = jeuRepository.findById(noJeu)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        existing.setTitre(jeu.getTitre());
        existing.setReference(jeu.getReference());
        existing.setAge_min(jeu.getAge_min());
        existing.setDescription(jeu.getDescription());
        existing.setDuree(jeu.getDuree());
        existing.setTarif_jour(jeu.getTarif_jour());
        existing.setGenres(jeu.getGenres());
        jeuRepository.save(existing);
        return existing;
    }
    public List<Map<String, Object>> getJeuxWithAvailability() {
        return jeuRepository.findAll().stream()
                .map(jeu -> {
                    Map<String, Object> info = new HashMap<>();
                    info.put("jeu", jeu);
                    info.put("availableCount", exemplaireRepository.countByJeuAndLouableTrue(jeu));
                    return info;
                })
                .collect(Collectors.toList());
    }

    public List<Jeu> getJeuxDisponiblesALocation() {
        return exemplaireRepository.findByLouableTrue()
                .stream()
                .map(Exemplaire::getJeu)
                .distinct()
                .collect(Collectors.toList());
    }

    public Jeu deleteJeu(int noJeu) {
        Jeu existing = jeuRepository.findById(noJeu)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        jeuRepository.deleteById(noJeu);
        return existing;
    }
}