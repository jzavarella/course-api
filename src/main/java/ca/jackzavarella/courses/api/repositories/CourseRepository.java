package ca.jackzavarella.courses.api.repositories;

import ca.jackzavarella.courses.api.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
public interface CourseRepository extends MongoRepository<Course, String> {
    void deleteAllByInstitutionAndAcademicYearAndProgramLevel(String institution, String academicYear, String programLevel);
    List<Course> findAllByInstitutionAndAcademicYearAndProgramLevel(String institution, String academicYear, String programLevel);
}
