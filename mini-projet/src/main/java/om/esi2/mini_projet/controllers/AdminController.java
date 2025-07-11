package om.esi2.mini_projet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import om.esi2.mini_projet.models.User;
import om.esi2.mini_projet.services.UserService;


@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("message", "Bienvenue dans l'espace admin !");
        return "admin-dashboard";
    }

    @PostMapping("/admin/users/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user); // assure-toi que userService existe et est injecté
        return "redirect:/users/users/list";
    }
}
