package com.example.recettes.Controller;



import com.example.recettes.Service.RecetteService;

import com.example.recettes.entites.Commentaire;
import com.example.recettes.entites.Ingrédient;
import com.example.recettes.entites.Recette;

import com.example.recettes.répository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/recettes")
public class RecetteController {

    @Autowired
    private RecetteService recetteService;
    @Autowired
    private RecetteRepository recetteRepository;

    // Endpoint pour récupérer toutes les recettes
    @GetMapping
    public List<Recette> getAllRecettes() {
        return recetteService.getAllRecettes();
    }

    // Endpoint pour récupérer une recette par son ID
    @GetMapping("/{id}")
    public Recette getRecetteById(@PathVariable Long id) {
        return recetteService.getRecetteById(id);
    }

    // Endpoint pour créer une nouvelle recette
    @PostMapping("/ajouter")
    public String createRecette(@ModelAttribute Recette nouvelleRecette, Model model,@RequestParam("imageUrl") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            try {
                // Définir le répertoire de stockage
                String uploadDir = "public/images/";
                Path uploadPath = Paths.get(uploadDir);

                // Vérifier si le répertoire de stockage existe, sinon le créer
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Sauvegarder le fichier dans le répertoire de stockage
                Path filePath = uploadPath.resolve(file.getOriginalFilename());

                Files.copy(file.getInputStream(), filePath);

                // Définir le chemin de l'image dans l'entité Recette
                nouvelleRecette.setImage(file.getOriginalFilename().toString());

            } catch (Exception e) {
                e.printStackTrace();
                // Gérer l'exception
            }
        }


        List<Ingrédient> ingrédients = nouvelleRecette.getIngrédients();
System.out.println(ingrédients);
        recetteService.createRecette(nouvelleRecette);
        List<Recette> recettes = recetteRepository.findAll();
        model.addAttribute("recettes", recettes);

        return "fruitables-1.0.0/index";
    }

    // Endpoint pour mettre à jour une recette existante
    @PutMapping("/{id}")
    public Recette updateRecette(@PathVariable Long id, @RequestBody Recette recette) {
        return recetteService.updateRecette(id, recette);
    }

    // Endpoint pour supprimer une recette par son ID
    @DeleteMapping("/{id}")
    public void deleteRecette(@PathVariable Long id) {
        recetteService.deleteRecette(id);
    }
    @GetMapping("/recette")
    public String showRecettesPage(Model model) {
        List<Recette> recettes = recetteRepository.findAll();
        model.addAttribute("recettes", recettes);
        String query=null;
        model.addAttribute("query",query);
        return "fruitables-1.0.0/index";
    }
    @GetMapping("/ajouter")
    public String ajou(Model model) {
Recette recette=new Recette();
Ingrédient ingrédient=new Ingrédient();
model.addAttribute("ingrédients",ingrédient);
model.addAttribute("nouvelleRecette",recette);


        return "fruitables-1.0.0/chackout";
    }
    @GetMapping("/search")
    public String searchRecettes(@RequestParam(name = "query") String query, Model model) {
        List<Recette> recettes = recetteService.searchRecettes(query);
        model.addAttribute("recettes", recettes);
        return "fruitables-1.0.0/index";
    }
    @GetMapping("/recettes/{id}")
    public String afficherDetailsRecette(@PathVariable Long id, Model model) {
        Recette recette = recetteService.getRecetteById(id);
        model.addAttribute("recette", recette);
        List<Commentaire> commentaires = recetteService.getCommentairesByRecetteId(id);
        model.addAttribute("commentaires", commentaires);
        List<Ingrédient> ingrédients = recetteService.getIngredientsByRecetteId(id);
        model.addAttribute("ingrédients", ingrédients);
        Commentaire commentaire=new Commentaire();
        model.addAttribute("commentaire",commentaire);
        return "fruitables-1.0.0/shop-detail";
    }
    private List<Ingrédient> ingredients = new ArrayList<>();
    @PostMapping("/ajouter-ingredient")
    public String ajouterIngredient(@RequestParam String nom, @RequestParam String quantite) {
        Ingrédient ingredient = new Ingrédient();
        ingredient.setNom(nom);
        ingredient.setQuantité(quantite);
        ingredients.add(ingredient);
        return "Ingrédient ajouté avec succès : " + nom + " (" + quantite + ")";
    }
}

