package ca.jackzavarella.courses.api.services;

import ca.jackzavarella.courses.api.dto.InstitutionMetaData;
import ca.jackzavarella.courses.api.models.Course;
import ca.jackzavarella.courses.api.models.Institution;
import ca.jackzavarella.courses.api.repositories.CourseRepository;
import ca.jackzavarella.courses.api.repositories.InstitutionRepository;
import ca.jackzavarella.courses.pdf.dto.ParsedCourses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
@Service
public class DatabaseService {

    private final CourseRepository courseRepository;
    private final InstitutionRepository institutionRepository;

    public DatabaseService(CourseRepository courseRepository, InstitutionRepository institutionRepository) {
        this.courseRepository = courseRepository;
        this.institutionRepository = institutionRepository;
    }

    public void saveParsedCourses(ParsedCourses parsedCourses) {
        Institution institution = this.institutionRepository.findInstitutionByName(parsedCourses.getInstitution());
        if (institution == null) {
            institution = new Institution();
            institution.setName(parsedCourses.getInstitution());
        }

        InstitutionMetaData metaData = parsedCourses.getMetaData();
        if (!institution.getData().contains(metaData)) {
            institution.getData().add(metaData);
        } else {
            this.courseRepository.deleteAllByInstitutionAndAcademicYearAndProgramLevel(institution.getName(), metaData.getAcademicYear(), metaData.getProgramLevel());
        }

        this.institutionRepository.save(institution);
        this.courseRepository.saveAll(parsedCourses);
    }

    public Page<Institution> fetchAllInstitutions(int pageSize, int pageNumber) {
        return this.institutionRepository.findAll(PageRequest.of(pageSize, pageNumber));
    }

    public Institution fetchInstitutionByName(String institution) {
        return this.institutionRepository.findInstitutionByName(institution);
    }

    public Page<Course> fetchCoursesWithQueries(String institution, String academicYear, String programLevel, int page, int size, HashMap<String, String> queries) {
        return this.courseRepository.findAllWithQueries(institution, academicYear, programLevel, queries, page, size);
    }

    public String fetchLatestAcademicYear(String institution) {
        Institution fetchedInstitution = this.institutionRepository.findInstitutionByName(institution);
        String latest = fetchedInstitution.getData().get(0).getAcademicYear();
        for (int i = 1; i < fetchedInstitution.getData().size(); i++) {
            if (fetchedInstitution.getData().get(i).getAcademicYear().compareToIgnoreCase(latest) > 0) {
                latest = fetchedInstitution.getData().get(i).getAcademicYear();
            }
        }
        return latest;
    }

    public Course fetchCourses(String institution, String academicYear, String programLevel, String courseCode) {
        return this.courseRepository.findByInstitutionAndAcademicYearAndProgramLevelAndCode(institution, academicYear, programLevel, courseCode);
    }
}
