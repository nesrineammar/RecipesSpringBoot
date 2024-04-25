package com.example.recettes.Service;

import com.example.recettes.entites.Commentaire;
import com.example.recettes.entites.Ingrédient;
import com.example.recettes.entites.Recette;
import com.example.recettes.répository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecetteService {

    @Autowired
    private RecetteRepository recetteRepository;

    public List<Recette> getAllRecettes() {
        return recetteRepository.findAll();
    }

    public Recette getRecetteById(Long id) {
        Optional<Recette> optionalRecette = recetteRepository.findById(id);
        if (optionalRecette.isPresent()) {
            return optionalRecette.get();
        } else {
            // Gérer le cas où la recette n'est pas trouvée
            throw new RuntimeException("Recette introuvable pour l'ID : " + id);
        }
    }

    public Recette createRecette(Recette recette) {
        List<Ingrédient> ingredients = recette.getIngrédients();
        System.out.println(ingredients);
        for (Ingrédient ingredient : ingredients) {
            ingredient.setRecette(recette); // Assurez-vous que chaque ingrédient est lié à la recette enregistrée
            // Enregistrez l'ingrédient
            // Ingredient savedIngredient = ingredientRepository.save(ingredient); // À décommenter si vous utilisez un repository d'ingrédients
        }
        return recetteRepository.save(recette);
    }

    public Recette updateRecette(Long id, Recette recette) {
        if (recetteRepository.existsById(id)) {
            recette.setId(id);
            return recetteRepository.saveAndFlush(recette);
        } else {
            // Gérer le cas où la recette n'existe pas
            throw new RuntimeException("Impossible de mettre à jour la recette car l'ID : " + id + " n'existe pas");
        }
    }

    public void deleteRecette(Long id) {
        if (recetteRepository.existsById(id)) {
            recetteRepository.deleteById(id);
        } else {
            // Gérer le cas où la recette n'existe pas
            throw new RuntimeException("Impossible de supprimer la recette car l'ID : " + id + " n'existe pas");
        }
    }
    public List<Recette> searchRecettes(String query) {
        // Implémentez ici la logique de recherche des recettes en fonction de la requête
        // Par exemple, vous pouvez utiliser recetteRepository.findByTitreContaining(query)
        // pour rechercher les recettes dont le titre contient la requête.
        // Vous pouvez également combiner plusieurs critères de recherche selon vos besoins.
        return recetteRepository.findByTitreContaining(query);
    }
    public List<Commentaire> getCommentairesByRecetteId(Long recetteId) {
        Recette recette = recetteRepository.findById(recetteId).orElse(null);
        if (recette != null) {
            return recette.getCommentaires();
        }
        return Collections.emptyList();
    }
    public List<Ingrédient> getIngredientsByRecetteId(Long recetteId) {
        Recette recette = recetteRepository.findById(recetteId).orElse(null);
        if (recette != null) {
            return recette.getIngrédients();
        }
        return Collections.emptyList();
    }
}

