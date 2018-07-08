package ca.jackzavarella.courses.pdf.util;

import ca.jackzavarella.courses.api.dto.InstitutionMetaData;
import ca.jackzavarella.courses.api.models.Course;
import ca.jackzavarella.courses.pdf.dto.ParsedCourses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
public class CourseUtil {
    public static Course convertParsedCourseToCourse(Course parsedCourse, InstitutionMetaData metaData) {
        Course course = new Course();
        course.setCode(parsedCourse.getCode());
        course.setTitle(parsedCourse.getTitle());
        course.setDescription(parsedCourse.getDescription());
        course.setOfferings(parsedCourse.getOfferings());
        course.setEquates(parsedCourse.getEquates());
        course.setPrerequisites(parsedCourse.getPrerequisites());
        course.setCorequisites(parsedCourse.getCorequisites());
        course.setRestrictions(parsedCourse.getRestrictions());
        course.setDepartments(parsedCourse.getDepartments());
        course.setSemesters(parsedCourse.getSemesters());
        course.setWeight(parsedCourse.getWeight());

        return course;
    }

    public static List<Course> convertParsedCoursesToCourseList(ParsedCourses courses) {
        List<Course> courseList = new ArrayList<>();
        InstitutionMetaData metaData = courses.getMetaData();
        for (Course course : courses) {
            courseList.add(convertParsedCourseToCourse(course, metaData));
        }
        return courseList;
    }
}
