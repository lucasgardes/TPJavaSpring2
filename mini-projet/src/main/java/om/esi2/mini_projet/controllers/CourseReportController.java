package om.esi2.mini_projet.controllers;

import lombok.RequiredArgsConstructor;
import om.esi2.mini_projet.services.CourseReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class CourseReportController {
    private final CourseReportService courseReportService;

    @GetMapping("/csv")
    public void downloadCsvReport(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=courses_report.csv");

        PrintWriter writer = response.getWriter();
        courseReportService.generateCsvReport(writer);
    }
}
