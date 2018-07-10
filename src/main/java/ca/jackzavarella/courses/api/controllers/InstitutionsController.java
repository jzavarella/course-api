package ca.jackzavarella.courses.api.controllers;

import ca.jackzavarella.courses.api.models.Course;
import ca.jackzavarella.courses.api.models.Institution;
import ca.jackzavarella.courses.api.responses.PagedResponse;
import ca.jackzavarella.courses.api.services.DatabaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
@RestController
@RequestMapping("/institutions")
public class InstitutionsController {
    private final DatabaseService databaseService;

    @Value("${paged.response.max-page-size}")
    private int MAX_PAGE_SIZE;

    public InstitutionsController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping
    public PagedResponse<Institution> getInstitutions(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize) {
        Page<Institution> page = this.databaseService.fetchAllInstitutions(pageNumber, pageSize);
        return new PagedResponse<>(page.getContent(), page.getTotalElements(), page.getTotalPages(), page.getPageable().getPageNumber(), page.getPageable().getPageSize());
    }

    @GetMapping("/{institution}")
    public Institution getInstitution(@PathVariable String institution) {
        return this.databaseService.fetchInstitutionByName(institution);
    }

    @GetMapping("/{institution}/courses")
    public PagedResponse<Course> getCourses(
            @PathVariable String institution,
            @RequestParam(required = false) String academicYear,
            @RequestParam(defaultValue = "Undergraduate") String programLevel,
            @RequestParam(defaultValue = "") String codeContains,
            @RequestParam(defaultValue = "") String titleContains,
            @RequestParam(defaultValue = "") String descriptionContains,
            @RequestParam(defaultValue = "") String offeringsContains,
            @RequestParam(defaultValue = "") String equatesContains,
            @RequestParam(defaultValue = "") String prerequisitesContains,
            @RequestParam(defaultValue = "") String corequisitesContains,
            @RequestParam(defaultValue = "") String restrictionsContains,
            @RequestParam(defaultValue = "") String departmentsContains,
            @RequestParam(defaultValue = "") String semestersContains,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize) throws Exception {
        if (academicYear == null) {
            academicYear = this.databaseService.fetchLatestAcademicYear(institution);
        }
        if (pageSize > this.MAX_PAGE_SIZE) {
            throw new Exception("pageSize must not exceed the max page size of " + this.MAX_PAGE_SIZE);
        }
        HashMap<String, String> queries = new HashMap<>();
        queries.put("codeContains", codeContains);
        queries.put("titleContains", titleContains);
        queries.put("descriptionContains", descriptionContains);
        queries.put("offeringsContains", offeringsContains);
        queries.put("equatesContains", equatesContains);
        queries.put("prerequisitesContains", prerequisitesContains);
        queries.put("corequisitesContains", corequisitesContains);
        queries.put("restrictionsContains", restrictionsContains);
        queries.put("departmentsContains", departmentsContains);
        queries.put("semestersContains", semestersContains);

        Page<Course> coursePage = this.databaseService.fetchCoursesWithQueries(institution, academicYear, programLevel, pageNumber, pageSize, queries);
        return new PagedResponse<>(coursePage.getContent(), coursePage.getTotalElements(), coursePage.getTotalPages(), coursePage.getPageable().getPageNumber(), coursePage.getPageable().getPageSize());
    }
}
