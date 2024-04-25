package com.example.recettes.Controller;

import com.example.recettes.Service.CommentaireService;
import com.example.recettes.Service.RecetteService;
import com.example.recettes.entites.Commentaire;
import com.example.recettes.entites.Recette;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private RecetteService recetteService;

    // Endpoint pour récupérer tous les commentaires d'une recette par son ID
    @GetMapping("/recette/{recetteId}")
    public List<Commentaire> getCommentairesByRecetteId(@PathVariable Long recetteId) {
        return commentaireService.getCommentairesByRecetteId(recetteId);
    }

    // Endpoint pour créer un nouveau commentaire
    @PostMapping
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
        return commentaireService.createCommentaire(commentaire);
    }

    // Endpoint pour mettre à jour un commentaire existant
    @PutMapping("/{id}")
    public Commentaire updateCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaire) {
        return commentaireService.updateCommentaire(id, commentaire);
    }

    // Endpoint pour supprimer un commentaire par son ID
    @DeleteMapping("/{id}")
    public void deleteCommentaire(@PathVariable Long id) {
        commentaireService.deleteCommentaire(id);
    }
    @PostMapping("/recettes/{id}/commentaires")
    public String ajouterCommentaire(@PathVariable Long id, @ModelAttribute Commentaire commentaire, Model model) {
        Recette recette = recetteService.getRecetteById(id);
        commentaire.setRecette(recette);
        commentaireService.createCommentaire(commentaire);

        model.addAttribute("recette", recette);

        List<Commentaire> commentaires = recetteService.getCommentairesByRecetteId(id);
        model.addAttribute("commentaires", commentaires);
        return "fruitables-1.0.0/shop-detail";
    }
}

