package com.example.recettes.Controller;

import com.example.recettes.Service.IngrédientService;
import com.example.recettes.entites.Ingrédient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngrédientController {

    @Autowired
    private IngrédientService ingrédientService;

    // Endpoint pour récupérer tous les ingrédients d'une recette par son ID
    @GetMapping("/recette/{recetteId}")
    public List<Ingrédient> getIngrédientsByRecetteId(@PathVariable Long recetteId) {
        return ingrédientService.getIngrédientsByRecetteId(recetteId);
    }

    // Endpoint pour créer un nouvel ingrédient
    @PostMapping
    public Ingrédient createIngrédient(@RequestBody Ingrédient ingrédient) {
        return ingrédientService.createIngrédient(ingrédient);
    }

    // Endpoint pour mettre à jour un ingrédient existant
    @PutMapping("/{id}")
    public Ingrédient updateIngrédient(@PathVariable Long id, @RequestBody Ingrédient ingrédient) {
        return ingrédientService.updateIngrédient(id, ingrédient);
    }

    // Endpoint pour supprimer un ingrédient par son ID
    @DeleteMapping("/{id}")
    public void deleteIngrédient(@PathVariable Long id) {
        ingrédientService.deleteIngrédient(id);
    }
}

