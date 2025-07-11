package om.esi2.mini_projet.controllers;

import om.esi2.mini_projet.models.DeclarationFrais;
import om.esi2.mini_projet.services.DeclarationFraisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/declarations")
public class DeclarationFraisController {

    private final DeclarationFraisService declarationFraisService;

    @Autowired
    public DeclarationFraisController(DeclarationFraisService declarationFraisService) {
        this.declarationFraisService = declarationFraisService;
    }

    // Endpoint pour créer une déclaration
    @PostMapping("/ajouter")
    public DeclarationFrais creerDeclaration(@RequestBody DeclarationFrais declarationFrais) {
        return declarationFraisService.creerDeclaration(declarationFrais);
    }

    // Endpoint pour récupérer une déclaration par ID
    @GetMapping("/{id}")
    public Optional<DeclarationFrais> getDeclarationById(@PathVariable Long id) {
        return declarationFraisService.getDeclarationById(id);
    }

    // Endpoint pour récupérer toutes les déclarations d'un professeur
    @GetMapping("/professeur/{professeurId}")
    public List<DeclarationFrais> getDeclarationsByProfesseur(@PathVariable Long professeurId) {
        return declarationFraisService.getDeclarationsByProfesseur(professeurId);
    }

    // Endpoint pour mettre à jour une déclaration
    @PutMapping("/modifier")
    public DeclarationFrais updateDeclaration(@RequestBody DeclarationFrais declarationFrais) {
        return declarationFraisService.updateDeclaration(declarationFrais);
    }

    // Endpoint pour supprimer une déclaration
    @DeleteMapping("/supprimer/{id}")
    public void deleteDeclaration(@PathVariable Long id) {
        declarationFraisService.deleteDeclaration(id);
    }
}
