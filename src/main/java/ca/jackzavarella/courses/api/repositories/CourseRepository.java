package ca.jackzavarella.courses.api.repositories;

import ca.jackzavarella.courses.api.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashMap;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
public interface CourseRepository extends MongoRepository<Course, String> {
    void deleteAllByInstitutionAndAcademicYearAndProgramLevel(String institution, String academicYear, String programLevel);

    Page<Course> findAllByInstitutionAndAcademicYearAndProgramLevelAndCodeIsLikeAndTitleIsLikeAndDescriptionIsLikeAndOfferingsIsLikeAndEquatesIsLikeAndPrerequisitesIsLikeAndCorequisitesIsLikeAndRestrictionsIsLikeAndDepartmentsIsLikeAndSemestersIsLike(String institution, String academicYear, String programLevel, String code, String title, String description, String offerings, String equates, String prerequisites, String corequisites, String restrictions, String departments, String semesters, Pageable pageable);

    default Page<Course> findAllWithQueries(String institution, String academicYear, String programLevel, HashMap<String, String> queries, int page, int size) {
        return findAllByInstitutionAndAcademicYearAndProgramLevelAndCodeIsLikeAndTitleIsLikeAndDescriptionIsLikeAndOfferingsIsLikeAndEquatesIsLikeAndPrerequisitesIsLikeAndCorequisitesIsLikeAndRestrictionsIsLikeAndDepartmentsIsLikeAndSemestersIsLike(institution, academicYear, programLevel, queries.get("codeContains"), queries.get("titleContains"), queries.get("descriptionContains"), queries.get("offeringsContains"), queries.get("equatesContains"), queries.get("prerequisitesContains"), queries.get("corequisitesContains"), queries.get("restrictionsContains"), queries.get("departmentsContains"), queries.get("semestersContains"), PageRequest.of(page, size));
    }
}
