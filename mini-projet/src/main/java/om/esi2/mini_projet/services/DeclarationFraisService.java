package om.esi2.mini_projet.services;

import om.esi2.mini_projet.models.DeclarationFrais;
import om.esi2.mini_projet.repositories.DeclarationFraisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeclarationFraisService {

    private final DeclarationFraisRepository declarationFraisRepository;

    @Autowired
    public DeclarationFraisService(DeclarationFraisRepository declarationFraisRepository) {
        this.declarationFraisRepository = declarationFraisRepository;
    }

    // Créer une déclaration
    public DeclarationFrais creerDeclaration(DeclarationFrais declarationFrais) {
        return declarationFraisRepository.save(declarationFrais);
    }

    // Récupérer une déclaration par ID
    public Optional<DeclarationFrais> getDeclarationById(Long id) {
        return declarationFraisRepository.findById(id);
    }

    public List<DeclarationFrais> getDeclarationsByProfesseur(Long professeurId) {
        return declarationFraisRepository.findByUserIdAndRoleProfesseur(professeurId);
    }

    // Mettre à jour une déclaration
    public DeclarationFrais updateDeclaration(DeclarationFrais declarationFrais) {
        return declarationFraisRepository.save(declarationFrais);
    }

    // Supprimer une déclaration
    public void deleteDeclaration(Long id) {
        declarationFraisRepository.deleteById(id);
    }
}
