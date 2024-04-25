package com.example.recettes.répository;

import com.example.recettes.entites.Ingrédient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngrédientRepository extends JpaRepository<Ingrédient,Long> {
    List<Ingrédient> findByRecetteId(Long recetteId);
}
