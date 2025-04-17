package fr.eni.ludotheque.classes;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Jeu")
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Integer no_jeu;

    @NonNull
    String titre;

    @NonNull
    String reference;

    int age_min;

    String description;

    int duree;

    double tarif_jour;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="jeu_genre",
               joinColumns = {@JoinColumn(name="no_jeu")},
               inverseJoinColumns = {@JoinColumn(name="no_genre")})
    List<Genre> genres;
}
