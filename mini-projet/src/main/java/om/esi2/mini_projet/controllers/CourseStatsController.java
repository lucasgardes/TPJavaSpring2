package om.esi2.mini_projet.controllers;

import lombok.RequiredArgsConstructor;
import om.esi2.mini_projet.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class CourseStatsController {
    private final CourseService courseService;

    // 📊 Nombre total de cours
    @GetMapping("/total-courses")
    public long getTotalCourses() {
        return courseService.getTotalCourses();
    }

    // 📊 Nombre de cours par professeur
    @GetMapping("/courses-by-professor")
    public Map<String, Long> getCoursesByProfessor() {
        return courseService.getCoursesCountByProfessor();
    }
}
