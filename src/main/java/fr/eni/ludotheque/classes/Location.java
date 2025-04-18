package fr.eni.ludotheque.classes;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no_location;

    @NonNull
    Date date_debut;

    Date date_fin;

    double tarif_jour;

    @ManyToOne
    @JoinColumn(name = "no_exemplaire")
    Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "no_facture")
    Facture facture;

    @ManyToOne
    @JoinColumn(name = "no_client")
    Client client;
}
