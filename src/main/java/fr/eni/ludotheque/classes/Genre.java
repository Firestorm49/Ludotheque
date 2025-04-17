package fr.eni.ludotheque.classes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    Integer no_genre;

    @NonNull
    String libelle;
}
