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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Jeu jeu = (Jeu) o;

        if (getAge_min() != jeu.getAge_min())
            return false;
        if (getDuree() != jeu.getDuree())
            return false;
        if (Double.compare(jeu.getTarif_jour(), getTarif_jour()) != 0)
            return false;
        if (!getNo_jeu().equals(jeu.getNo_jeu()))
            return false;
        if (!getTitre().equals(jeu.getTitre()))
            return false;
        if (!getReference().equals(jeu.getReference()))
            return false;
        if (getDescription() != null ? !getDescription().equals(jeu.getDescription()) : jeu.getDescription() != null)
            return false;
        return getGenres() != null ? getGenres().equals(jeu.getGenres()) : jeu.getGenres() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getNo_jeu().hashCode();
        result = 31 * result + getTitre().hashCode();
        result = 31 * result + getReference().hashCode();
        result = 31 * result + getAge_min();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getDuree();
        temp = Double.doubleToLongBits(getTarif_jour());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getGenres() != null ? getGenres().hashCode() : 0);
        return result;
    }
}
