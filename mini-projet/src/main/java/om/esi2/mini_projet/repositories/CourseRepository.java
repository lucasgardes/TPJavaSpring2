package om.esi2.mini_projet.repositories;

import om.esi2.mini_projet.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProfessorUsername(String username);
}
