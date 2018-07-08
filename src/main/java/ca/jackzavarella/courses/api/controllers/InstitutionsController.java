package ca.jackzavarella.courses.api.controllers;

import ca.jackzavarella.courses.api.models.Course;
import ca.jackzavarella.courses.api.models.Institution;
import ca.jackzavarella.courses.api.services.DatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
@RestController
@RequestMapping("/institutions")
public class InstitutionsController {
    private final DatabaseService databaseService;

    public InstitutionsController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping
    public List<Institution> getInstitutions() {
        return this.databaseService.fetchAllInstitutions();
    }

    @GetMapping("/{institution}")
    public Institution getInstitution(@PathVariable String institution) {
        return this.databaseService.fetchInstitutionByName(institution);
    }

    @GetMapping("/{institution}/courses")
    public List<Course> getCourses(@PathVariable String institution, @RequestParam(required = false) String academicYear, @RequestParam(required = false) String programLevel) {
        return this.databaseService.fetchCoursesWithQueries(institution, academicYear, programLevel);
    }
}
