package fr.eni.ludotheque.classes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_adresse")
    Integer no_adresse;

    @Column(name = "rue")
    @NonNull
    String rue;

    @Column(name = "code_postal")
    int code_postal;

    @Column(name = "ville")
    @NonNull
    String ville;

    @OneToOne(mappedBy = "adresse")
    private Client client;

    // Constructors

    public Adresse(int no_adresse, String rue, int code_postal, String ville) {
        this.no_adresse = no_adresse;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    public Adresse(String rue, int code_postal, String ville) {
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    // Getters & Setters

    public int getNo_adresse() {
        return no_adresse;
    }

    public void setNo_adresse(int no_adresse) {
        this.no_adresse = no_adresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
