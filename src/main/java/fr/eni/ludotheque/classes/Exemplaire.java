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
    @Column(name="code_barre")
    String code_barre;

    @NonNull
    @Column(name="louable")
    Boolean louable;

    @ManyToOne
    @JoinColumn(name = "no_jeu")
    Jeu jeu;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Exemplaire that = (Exemplaire) o;

        if (!getNo_exemplaire().equals(that.getNo_exemplaire()))
            return false;
        if (!getCode_barre().equals(that.getCode_barre()))
            return false;
        if (!getLouable().equals(that.getLouable()))
            return false;
        return getJeu() != null ? getJeu().equals(that.getJeu()) : that.getJeu() == null;
    }

    @Override
    public int hashCode() {
        int result = getNo_exemplaire().hashCode();
        result = 31 * result + getCode_barre().hashCode();
        result = 31 * result + getLouable().hashCode();
        result = 31 * result + (getJeu() != null ? getJeu().hashCode() : 0);
        return result;
    }
}
