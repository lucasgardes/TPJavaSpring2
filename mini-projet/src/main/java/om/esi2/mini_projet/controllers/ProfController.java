package om.esi2.mini_projet.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfController {

    @PreAuthorize("hasRole('PROFESSEUR')")
    @GetMapping("/prof/dashboard")
    public String profDashboard(Model model) {
        model.addAttribute("message", "Bienvenue sur l’espace Professeur !");
        return "prof-dashboard";
    }

    @GetMapping("/declaration")
    public String startDeclarationFlow() {
        return "redirect:/declarationFrais";
    }
}
