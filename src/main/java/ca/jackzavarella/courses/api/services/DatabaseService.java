package ca.jackzavarella.courses.api.services;

import ca.jackzavarella.courses.api.dto.InstitutionMetaData;
import ca.jackzavarella.courses.pdf.dto.ParsedCourses;
import ca.jackzavarella.courses.api.models.Course;
import ca.jackzavarella.courses.api.models.Institution;
import ca.jackzavarella.courses.api.repositories.CourseRepository;
import ca.jackzavarella.courses.api.repositories.InstitutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Institution> fetchAllInstitutions() {
        return this.institutionRepository.findAll();
    }

    public Institution fetchInstitutionByName(String institution) {
        return this.institutionRepository.findInstitutionByName(institution);
    }

    public List<Course> fetchCoursesWithQueries(String institution, String academicYear, String programLevel) {
        if (academicYear == null) {
            academicYear = this.fetchLatestAcademicYear(institution);
        }
        if (programLevel == null) {
            programLevel = "Undergraduate";
        }
        return this.courseRepository.findAllByInstitutionAndAcademicYearAndProgramLevel(institution, academicYear, programLevel);
        
    }

    private String fetchLatestAcademicYear(String institution) {
        Institution fetchedInstitution = this.institutionRepository.findInstitutionByName(institution);
        String latest = fetchedInstitution.getData().get(0).getAcademicYear();
        for (int i = 1; i < fetchedInstitution.getData().size(); i++) {
            if (fetchedInstitution.getData().get(i).getAcademicYear().compareToIgnoreCase(latest) > 0) {
                latest = fetchedInstitution.getData().get(i).getAcademicYear();
            }
        }
        return latest;
    }
}
