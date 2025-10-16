package com.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cacheable // Marquer l'entité comme cacheable
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Livre> livres = new HashSet<>();

    public Categorie() { }

    public Categorie(String nom) {
        this.nom = nom;
    }

    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<Livre> getLivres() { return livres; }
    public void setLivres(Set<Livre> livres) { this.livres = livres; }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
