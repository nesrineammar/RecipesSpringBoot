package com.example.recettes.entites;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String instructions;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date CreatedAt;
   private String image;
    private String categorie;

    @OneToMany(mappedBy = "recette", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingrédient> ingrédients= new ArrayList<>();

    @OneToMany(mappedBy = "recette", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Ingrédient> getIngrédients() {
        return ingrédients;
    }

    public void setIngrédients(List<Ingrédient> ingrédients) {
        this.ingrédients = ingrédients;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
