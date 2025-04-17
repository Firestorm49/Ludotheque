package fr.eni.ludotheque.classes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Exemplaire")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Integer no_exemplaire;

    @NonNull
    String code_barre;

    @NonNull
    Boolean louable;

    @ManyToOne
    @JoinColumn(name = "no_jeu")
    Jeu jeu;

}
