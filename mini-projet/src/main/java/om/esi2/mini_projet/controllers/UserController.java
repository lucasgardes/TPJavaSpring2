package om.esi2.mini_projet.controllers;

import om.esi2.mini_projet.models.Role;
import om.esi2.mini_projet.models.User;
import om.esi2.mini_projet.repositories.UserRepository;
import om.esi2.mini_projet.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    // @PostMapping
    // public User createUser(@RequestBody User user) {
    //     return userService.createUser(user);
    // }

    @PostMapping("/professeur")
    public User createProfesseur(@RequestParam String username, 
                                 @RequestParam String nom,
                                 @RequestParam String prenom, 
                                 @RequestParam String password) {
        return userService.createUser(username, nom, prenom, password, Role.PROFESSEUR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.of(userService.updateUser(id, user));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/list")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        System.out.println("Nombre d'utilisateurs trouvés : " + users.size());
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/users/users/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user-form";
    }
}
