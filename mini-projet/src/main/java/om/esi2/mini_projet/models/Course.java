package om.esi2.mini_projet.models;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private User professor; // Un professeur crée plusieurs cours
}
