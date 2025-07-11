package om.esi2.mini_projet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import om.esi2.mini_projet.model.*;
import om.esi2.mini_projet.models.DeclarationFrais;
import om.esi2.mini_projet.models.User;
import om.esi2.mini_projet.repositories.DeclarationFraisRepository;
import om.esi2.mini_projet.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Service
public class DeclarationService {

    @Autowired
    private DeclarationFraisRepository declarationFraisRepository;

    @Autowired
    private UserRepository userRepository;

    public void enregistrerDeclaration(DetailsFormation detailsFormation,
                                      FraisTransport fraisTransport,
                                      FraisHebergement fraisHebergement,
                                      FraisRestauration fraisRestauration,
                                      CoordonneesBancaires coordonneesBancaires) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        User user;

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Optional<User> userOpt = userRepository.findByUsername(username);
            if (userOpt.isEmpty()) {
                throw new IllegalStateException("Utilisateur non trouvé avec username : " + username);
            }
            user = userOpt.get();
        } else {
            throw new IllegalStateException("Principal n'est pas une instance de UserDetails");
        }

        DeclarationFrais declaration = new DeclarationFrais();

        declaration.setUser(user);
        declaration.setDateFormation(detailsFormation.getDate().atStartOfDay());
        declaration.setIntituleFormation(detailsFormation.getIntitule());
        declaration.setLieuFormation(detailsFormation.getLieu());
        declaration.setTypeTransport(fraisTransport.getTransport());
        declaration.setTypeHebergement(fraisHebergement.getHebergement());
        declaration.setTypeRestauration(fraisRestauration.getRestauration());
        declaration.setIban(coordonneesBancaires.getIban());
        declaration.setBic(coordonneesBancaires.getBic());
        declaration.setStatut("SOUMISE");

        declarationFraisRepository.save(declaration);
    }
}
