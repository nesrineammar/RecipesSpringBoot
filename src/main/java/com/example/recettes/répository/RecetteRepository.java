package com.example.recettes.répository;

import com.example.recettes.entites.Recette;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecetteRepository extends JpaRepository<Recette,Long> {
    List<Recette> findByTitreContaining(String keyword);
    List<Recette> findByCategorie(String keyword);
}
