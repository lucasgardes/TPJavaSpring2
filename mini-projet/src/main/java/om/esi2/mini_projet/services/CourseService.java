package om.esi2.mini_projet.services;

import lombok.RequiredArgsConstructor;
import om.esi2.mini_projet.models.Course;
import om.esi2.mini_projet.models.User;
import om.esi2.mini_projet.repositories.CourseRepository;
import om.esi2.mini_projet.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> updateCourse(Long id, Course course) {
        if (!courseRepository.existsById(id)) return Optional.empty();
        course.setId(id);
        return Optional.of(courseRepository.save(course));
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> getCoursesByProfessor(String username) {
        return courseRepository.findByProfessorUsername(username);
    }

    // 1️⃣ Nombre total de cours
    public long getTotalCourses() {
        return courseRepository.count();
    }

    // 2️⃣ Nombre de cours par professeur
    public Map<String, Long> getCoursesCountByProfessor() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .collect(Collectors.groupingBy(
                        course -> course.getProfessor().getUsername(),
                        Collectors.counting()
                ));
    }
}
