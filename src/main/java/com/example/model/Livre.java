package com.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cacheable // Marquer l'entité comme cacheable
@NamedEntityGraph(
        name = "graph.Livre.categoriesEtAuteur",
        attributeNodes = {
                @NamedAttributeNode("categories"),
                @NamedAttributeNode("auteur")
        }
)
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private Integer anneePublication;

    private String isbn;

    private String resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "livre_categorie",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<Categorie> categories = new HashSet<>();

    public Livre() { }

    public Livre(String titre) {
        this.titre = titre;
    }

    public Livre(String titre, Integer anneePublication, String isbn) {
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
    }

    public void addCategorie(Categorie categorie) {
        categories.add(categorie);
        categorie.getLivres().add(this);
    }

    public void removeCategorie(Categorie categorie) {
        categories.remove(categorie);
        categorie.getLivres().remove(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public Integer getAnneePublication() { return anneePublication; }
    public void setAnneePublication(Integer anneePublication) { this.anneePublication = anneePublication; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }

    public Auteur getAuteur() { return auteur; }
    public void setAuteur(Auteur auteur) { this.auteur = auteur; }

    public Set<Categorie> getCategories() { return categories; }
    public void setCategories(Set<Categorie> categories) { this.categories = categories; }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", anneePublication=" + anneePublication +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
