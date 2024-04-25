package com.example.recettes.Service;

import com.example.recettes.entites.Ingrédient;
import com.example.recettes.répository.IngrédientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IngrédientService {

    @Autowired
    private IngrédientRepository ingrédientRepository;

    public List<Ingrédient> getIngrédientsByRecetteId(Long recetteId) {
        return ingrédientRepository.findByRecetteId(recetteId);
    }

    public Ingrédient createIngrédient(Ingrédient ingrédient) {
        return ingrédientRepository.save(ingrédient);
    }

    public Ingrédient updateIngrédient(Long id, Ingrédient ingrédient) {
        if (ingrédientRepository.existsById(id)) {
            ingrédient.setId(id);
            return ingrédientRepository.saveAndFlush(ingrédient);
        } else {
            throw new RuntimeException("Impossible de mettre à jour l'ingrédient car l'ID : " + id + " n'existe pas");
        }
    }

    public void deleteIngrédient(Long id) {
        if (ingrédientRepository.existsById(id)) {
            ingrédientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Impossible de supprimer l'ingrédient car l'ID : " + id + " n'existe pas");
        }
    }
}

