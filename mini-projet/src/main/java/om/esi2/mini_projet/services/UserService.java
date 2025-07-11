package om.esi2.mini_projet.services;

import om.esi2.mini_projet.models.User;
import om.esi2.mini_projet.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import om.esi2.mini_projet.models.Role;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String username, String nom, String prenom, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) return Optional.empty();
        user.setId(id);
        return Optional.of(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            System.out.println("❌ Utilisateur introuvable : " + username);
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }
        System.out.println("✅ Utilisateur trouvé : " + user.get().getUsername() + " - " + user.get().getRole());
        return user.map(u -> org.springframework.security.core.userdetails.User
            .withUsername(u.getUsername())
            .password(u.getPassword())
            .authorities(u.getRole().name())
            .build())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
