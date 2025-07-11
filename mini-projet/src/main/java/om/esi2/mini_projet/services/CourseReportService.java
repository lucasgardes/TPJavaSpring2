package om.esi2.mini_projet.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseReportService {
    private final CourseService courseService;

    public void generateCsvReport(PrintWriter writer) {
        Map<String, Long> stats = courseService.getCoursesCountByProfessor();

        writer.println("Professeur,Nombre de Cours");

        stats.forEach((professor, count) -> writer.println(professor + "," + count));
    }
}
