package om.esi2.mini_projet.repositories;

import om.esi2.mini_projet.models.DeclarationFrais;
import om.esi2.mini_projet.models.StatutDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationFraisRepository extends JpaRepository<DeclarationFrais, Long> {
    
    // Trouver toutes les déclarations d'un professeur par son ID
    @Query("SELECT d FROM DeclarationFrais d WHERE d.user.id = :userId AND d.user.role = om.esi2.mini_projet.models.Role.PROFESSEUR")
    List<DeclarationFrais> findByUserIdAndRoleProfesseur(@Param("userId") Long userId);

    // Trouver toutes les déclarations avec un statut spécifique
    List<DeclarationFrais> findByStatut(StatutDeclaration statut);
}
