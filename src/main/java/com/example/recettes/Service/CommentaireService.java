package com.example.recettes.Service;

import com.example.recettes.entites.Commentaire;
import com.example.recettes.répository.CommentaireRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepsitory commentaireRepository;

    public List<Commentaire> getCommentairesByRecetteId(Long recetteId) {
        return commentaireRepository.findByRecetteId(recetteId);
    }

    public Commentaire createCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    public Commentaire updateCommentaire(Long id, Commentaire commentaire) {
        if (commentaireRepository.existsById(id)) {
            commentaire.setId(id);
            return commentaireRepository.saveAndFlush(commentaire);
        } else {
            throw new RuntimeException("Impossible de mettre à jour le commentaire car l'ID : " + id + " n'existe pas");
        }
    }

    public void deleteCommentaire(Long id) {
        if (commentaireRepository.existsById(id)) {
            commentaireRepository.deleteById(id);
        } else {
            throw new RuntimeException("Impossible de supprimer le commentaire car l'ID : " + id + " n'existe pas");
        }
    }
}

