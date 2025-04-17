package fr.eni.ludotheque.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.classes.Adresse;
import fr.eni.ludotheque.dal.AdresseRepository;

@Service
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    public List<Adresse> findAll() {
        return adresseRepository.findAll();
    }

    public Adresse findById(int no_adresse) {
        return adresseRepository.findById(no_adresse)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    public Adresse updateAdresse(int no_adresse, Adresse adresse) {
        Adresse existingAdresse = adresseRepository.findById(no_adresse)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        existingAdresse.setRue(adresse.getRue());
        existingAdresse.setCode_postal(adresse.getCode_postal());
        existingAdresse.setVille(adresse.getVille());
        adresseRepository.save(existingAdresse);
        return existingAdresse;
    }

    public Adresse deleteAdresse(int no_adresse) {
        Adresse existingAdresse = adresseRepository.findById(no_adresse)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        adresseRepository.deleteById(no_adresse);
        return existingAdresse;
    }
}
