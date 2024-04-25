package com.example.recettes.répository;

import com.example.recettes.entites.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepsitory extends JpaRepository<Commentaire,Long> {
    List<Commentaire> findByRecetteId(Long recetteId);
}
