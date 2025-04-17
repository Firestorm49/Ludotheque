package fr.eni.ludotheque.classes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_client")
    private int no_client;

    @Column(name = "nom")
    @NonNull
    private String nom;

    @Column(name = "prenom")
    @NonNull
    private String prenom;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "no_telephone")
    private String no_telephone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "no_adresse", referencedColumnName = "no_adresse")
    private Adresse adresse;

    // Constructors

    public Client(String nom, String prenom, String email, String no_telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.no_telephone = no_telephone;
    }

    public Client(int no_client, String nom, String prenom, String email, String no_telephone) {
        this.no_client = no_client;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.no_telephone = no_telephone;
    }

    // Getters & Setters

    public int getNo_client() {
        return no_client;
    }

    public void setNo_client(int no_client) {
        this.no_client = no_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telephone() {
        return no_telephone;
    }

    public void setNo_telephone(String no_telephone) {
        this.no_telephone = no_telephone;
    }

}
